import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

public class Haru {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Haru(Path folderPath, Path filePath) {
        ui = new Ui();
        storage = new Storage(folderPath, filePath);

        try {
            storage.verifyTaskFile();
            tasks = new TaskList(storage.loadTaskList());
        } catch (HaruException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(); // overwrites corrupted haru.txt
        }
    }

    public void run2() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse2(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HaruException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            try {
                String input = ui.readCommand();
                String[] parsedInput = Parser.parse(input);
                String command =  parsedInput[0];
                String arguments = parsedInput[1];

                switch (command) {
                case "list":
                    listHandler(tasks);
                    break;
                case "mark":
                case "unmark":
                    markHandler(tasks, command, arguments);
                    storage.updateTaskList(tasks);
                    break;
                case "todo":
                    todoHandler(tasks, arguments);
                    storage.updateTaskList(tasks);
                    break;
                case "deadline":
                    deadlineHandler(tasks, arguments);
                    storage.updateTaskList(tasks);
                    break;
                case "event":
                    eventHandler(tasks, arguments);
                    storage.updateTaskList(tasks);
                    break;
                case "delete":
                    deleteHandler(tasks, arguments);
                    storage.updateTaskList(tasks);
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

    public static void main(String[] args) {
        Path folderPath = Paths.get("src","data");
        Path filePath = Paths.get("src","data", "haru.txt");
        new Haru(folderPath, filePath).run2();
    }

    public static void listHandler(TaskList tasks) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }

        ui.showAllTasks(tasks);
    }

    public static void markHandler(TaskList tasks, String command, String arguments) throws HaruException {
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

    public static void todoHandler(TaskList tasks, String description) throws HaruException {
        if (description.isEmpty()) {
            throw new HaruException.InvalidTodoException();
        }
        Task newTask =  new Todo(description);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.size());
    }

    public static void deadlineHandler(TaskList tasks, String arguments) throws HaruException {
        if (!arguments.contains(" /by ")) {
            throw new HaruException.InvalidDeadlineException();
        }
        String[] argumentsArray = arguments.split(" /by ", 2);
        String description = argumentsArray[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y Hmm");
            LocalDateTime by = LocalDateTime.parse(argumentsArray[1], formatter);
            Task newTask =  new Deadline(description, by);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.size());
        } catch (DateTimeParseException e){
            throw new HaruException.DateTimeParseException();
        }
    }

    public static void eventHandler(TaskList tasks, String arguments) throws HaruException {
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

    public static void deleteHandler(TaskList tasks, String arguments) throws HaruException {
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