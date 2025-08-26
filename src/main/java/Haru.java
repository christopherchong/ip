import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Haru {
    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        ArrayList<Task> tasks;

        try {
            Storage.verifyTaskFile();
            tasks = Storage.loadTaskList();
        } catch (HaruException | IOException e) {
            ui.showError(e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        ui.showWelcomeMessage();

        while (true) {
            try {
                String input = sc.nextLine();
                String command;
                String arguments = "";

                if (input.contains(" ")) {
                    String[] inputArray = input.split(" ", 2);
                    command = inputArray[0];
                    arguments = inputArray[1];
                } else {
                    command = input;
                }

                switch (command) {
                case "list":
                    listHandler(tasks);
                    break;
                case "mark":
                case "unmark":
                    markHandler(tasks, command, arguments);
                    Storage.updateTaskList(tasks);
                    break;
                case "todo":
                    todoHandler(tasks, arguments);
                    Storage.updateTaskList(tasks);
                    break;
                case "deadline":
                    deadlineHandler(tasks, arguments);
                    Storage.updateTaskList(tasks);
                    break;
                case "event":
                    eventHandler(tasks, arguments);
                    Storage.updateTaskList(tasks);
                    break;
                case "delete":
                    deleteHandler(tasks, arguments);
                    Storage.updateTaskList(tasks);
                    break;
                case "bye":
                    ui.showExitMessage();
                    return;
                default:
                    throw new HaruException.InvalidCommandException();
                }
            } catch (HaruException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void listHandler(ArrayList<Task> tasks) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }

        ui.showAllTasks(tasks);
    }

    public static void markHandler(ArrayList<Task> tasks, String command, String arguments) throws HaruException {
        if (arguments.isEmpty() || !arguments.matches("\\d+")) {
            throw new HaruException.NumberFormatException();
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        if (taskIndex >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }

        Task task = tasks.get(taskIndex);
        if (command.equals("mark")) {
            if (task.getStatus().equals("X")) {
                throw new HaruException.MarkException();
            }
            task.markDone();
            ui.showMarkMessage(task);
        } else {
            if (task.getStatus().equals(" ")) {
                throw new HaruException.UnmarkException();
            }
            task.markUndone();
            ui.showUnmarkMessage(task);
        }
    }

    public static void todoHandler(ArrayList<Task> tasks, String description) throws HaruException {
        if (description.isEmpty()) {
            throw new HaruException.InvalidTodoException();
        }
        Task newTask =  new Todo(description);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.size());
    }

    public static void deadlineHandler(ArrayList<Task> tasks, String arguments) throws HaruException {
        if (!arguments.contains(" /by ")) {
            throw new HaruException.InvalidDeadlineException();
        }
        String[] argumentsArray = arguments.split(" /by ", 2);
        String description = argumentsArray[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y Hmm");
            LocalDateTime by = LocalDateTime.parse(argumentsArray[1], formatter);
            Task newTask =  new Deadline(true, description, by);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.size());
        } catch (DateTimeParseException e){
            throw new HaruException.DateTimeParseException();
        }
    }

    public static void eventHandler(ArrayList<Task> tasks, String arguments) throws HaruException {
        if (!arguments.contains(" /from ") || !arguments.contains(" /to ")
                || arguments.lastIndexOf(" /to ") < arguments.lastIndexOf(" /from ")) {
            throw new HaruException.InvalidEventException();
        }

        String[] argumentsArray = arguments.split(" /from ", 2);
        String description = argumentsArray[0];
        String dates = argumentsArray[1];
        String[] datesArray = dates.split(" /to ", 2);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y Hmm");
            LocalDateTime from = LocalDateTime.parse(datesArray[0], formatter);
            LocalDateTime to = LocalDateTime.parse(datesArray[1], formatter);
            if (to.isBefore(from)) {
                throw new HaruException.DateTimeOrderException();
            } else if (to.isEqual(from)) {
                throw new HaruException.SameDateTimeException();
            }

            Task newTask =  new Event(description, from, to);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.size());
        } catch (DateTimeParseException e){
            throw new HaruException.DateTimeParseException();
        }
    }

    public static void deleteHandler(ArrayList<Task> tasks, String arguments) throws HaruException {
        if (arguments.isEmpty() || !arguments.matches("\\d+")) {
            throw new HaruException.NumberFormatException();
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        if (taskIndex >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }

        String taskInfo = tasks.get(taskIndex).getTaskInfo();
        tasks.remove(taskIndex);
        ui.showDeletedTask(taskInfo, tasks.size());
    }
}