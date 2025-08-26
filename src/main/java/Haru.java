import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Haru {
    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        // Retrieval of task file
        Path folderPath = Paths.get("src","data");
        Path filePath = Paths.get("src","data", "haru.txt");
        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
                Files.createFile(filePath);
            } else if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            readTaskList(tasks, filePath);
        } catch (IOException e) {
            System.out.println("There was an unexpected error with task file!");
            return;
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println("The task file is corrupted!");
            return;
        }

        // Prepare for user input
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
                    updateTaskList(tasks, filePath);
                    break;
                case "todo":
                    todoHandler(tasks, arguments);
                    updateTaskList(tasks, filePath);
                    break;
                case "deadline":
                    deadlineHandler(tasks, arguments);
                    updateTaskList(tasks, filePath);
                    break;
                case "event":
                    eventHandler(tasks, arguments);
                    updateTaskList(tasks, filePath);
                    break;
                case "delete":
                    deleteHandler(tasks, arguments);
                    updateTaskList(tasks, filePath);
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

    private static void readTaskList(ArrayList<Task> tasks, Path filePath) throws IOException {
        File f = new File(filePath.toString());
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] arguments = task.split("\\|");
            boolean isDone = arguments[1].equals("1");
            String description = arguments[2];

            switch (task.charAt(0)) {
            case 'T': // e.g. T|1|read book
                tasks.add(new Todo(isDone, description));
                break;

            case 'D': // e.g. D|0|return book|2/12/2019 1800
                DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime by = LocalDateTime.parse(arguments[3], deadlineFormatter);
                tasks.add(new Deadline(isDone, description, by));
                break;

            case 'E': // e.g. E|0|project meeting|2/12/2019 1800|2/12/2019 1900
                DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime from = LocalDateTime.parse(arguments[3], eventFormatter);
                LocalDateTime to = LocalDateTime.parse(arguments[4], eventFormatter);
                tasks.add(new Event(isDone, description, from, to));
                break;
            }
        }
    }

    private static void updateTaskList(ArrayList<Task> tasks, Path filePath) throws IOException {
        FileWriter f = new FileWriter(filePath.toString());
        for (Task task : tasks) {
            f.write(task.getTaskInfoForFile() + System.lineSeparator());
        }
        f.close();
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