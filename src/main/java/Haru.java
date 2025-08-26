import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Haru {
    private static final String LINE = "____________________________________________________________";
    private static final String LINEN = "____________________________________________________________\n";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        // Retrieval of task file
        Path path = Paths.get("src","data", "haru.txt");
        try {
            readTaskList(tasks, path);
        } catch (IOException e) {
            System.out.println("There was an error with file operation!");
            return;
        }

        // Prepare for user input
        Scanner sc = new Scanner(System.in);
        greet();

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
                    break;
                case "todo":
                    todoHandler(tasks, arguments);
                    break;
                case "deadline":
                    deadlineHandler(tasks, arguments);
                    break;
                case "event":
                    eventHandler(tasks, arguments);
                    break;
                case "delete":
                    deleteHandler(tasks, arguments);
                    break;
                case "bye":
                    bye();
                    return;
                default:
                    throw new HaruException.InvalidCommandException();
                }
            } catch (HaruException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void readTaskList(ArrayList<Task> tasks, Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            return;
        }

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

            case 'D': // e.g. D|0|return book|June 6th
                String by = arguments[3];
                tasks.add(new Deadline(isDone, description, by));
                break;

            case 'E': // e.g. E|0|project meeting|Aug 6th 2-4pm
                String from = arguments[3];
                String to = arguments[4];
                tasks.add(new Event(isDone, description, from, to));
                break;
            }
        }
    }

    public static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Haru\nWhat can I do for you?");
        System.out.println(LINEN);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINEN);
    }

    public static void listHandler(ArrayList<Task> tasks) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }

        System.out.println(LINE);
        System.out.println("Here are the tasks that you've set:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).getTaskInfo());
        }
        System.out.println(LINEN);
    }

    public static void markHandler(ArrayList<Task> tasks, String command, String arguments) throws HaruException {
        if (arguments.isEmpty() || !arguments.matches("\\d+")) {
            throw new HaruException.NumberFormatException();
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        if (taskIndex >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }

        if (command.equals("mark")) {
            if (tasks.get(taskIndex).getStatus().equals("X")) {
                throw new HaruException.MarkException();
            }
            tasks.get(taskIndex).markDone();
            System.out.println(LINE);
            System.out.println("Nice! I've marked this task as done:");
        } else {
            if (tasks.get(taskIndex).getStatus().equals(" ")) {
                throw new HaruException.UnmarkException();
            }
            tasks.get(taskIndex).markUndone();
            System.out.println(LINE);
            System.out.println("Got it! I've marked this task as not done yet:");
        }
        System.out.println("\t" + tasks.get(taskIndex).getTaskInfo());
        System.out.println(LINEN);
    }

    public static void todoHandler(ArrayList<Task> tasks, String description) throws HaruException {
        if (description.isEmpty()) {
            throw new HaruException.InvalidTodoException();
        }

        tasks.add(new Todo(description));
        displayTask(tasks, tasks.size() - 1);
    }

    public static void deadlineHandler(ArrayList<Task> tasks, String arguments) throws HaruException {
        if (!arguments.contains(" /by ")) {
            throw new HaruException.InvalidDeadlineException();
        }

        String[] argumentsArray = arguments.split(" /by ", 2);
        String description = argumentsArray[0];
        String by = argumentsArray[1];
        tasks.add(new Deadline(description, by));
        displayTask(tasks, tasks.size() - 1);
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
        String from = datesArray[0];
        String to = datesArray[1];
        tasks.add(new Event(description, from, to));
        displayTask(tasks, tasks.size() - 1);
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
        System.out.println(LINE);
        System.out.println("Understood! I've removed this task:\n\t" + taskInfo);
        System.out.println("There are now " + tasks.size() + " task(s)!");
        System.out.println(LINEN);
    }

    public static void displayTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(taskIndex).getTaskInfo());
        System.out.println("There are now " + tasks.size() + " task(s)!");
        System.out.println(LINEN);
    }
}