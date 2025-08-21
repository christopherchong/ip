import java.util.Scanner;
import java.util.ArrayList;

public class Haru {
    private static final String LINE = "____________________________________________________________";
    private static final String LINEN = "____________________________________________________________\n";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        greet();

        while (true) {
            try {
                String input = sc.nextLine();
                String command;
                String arguments = "";
                int taskIndex;

                if (input.contains(" ")) {
                    String[] inputArray = input.split(" ", 2);
                    command = inputArray[0];
                    arguments = inputArray[1];
                }
                else {
                    command = input;
                }

                switch (command) {
                    case "list":
                        if (tasks.isEmpty()) {
                            throw new HaruException.NoTasksException();
                        }
                        list(tasks);
                        break;

                    case "mark":
                    case "unmark":
                        if (arguments.isEmpty() || !arguments.matches("\\d+")) {
                            throw new HaruException.NumberFormatException();
                        }
                        taskIndex = Integer.parseInt(arguments) - 1;
                        if (taskIndex >= tasks.size()) {
                            throw new HaruException.InvalidIndexException();
                        }

                        if (command.equals("mark")) {
                            if (tasks.get(taskIndex).getStatus().equals("X")) {
                                throw new HaruException.MarkException();
                            }
                            mark(tasks, taskIndex);
                        }
                        else {
                            if (tasks.get(taskIndex).getStatus().equals(" ")) {
                                throw new HaruException.UnmarkException();
                            }
                            unmark(tasks, taskIndex);
                        }
                        break;

                    case "todo":
                        if (arguments.isEmpty()) {
                            throw new HaruException.InvalidTodoException();
                        }
                        toDo(tasks, arguments);
                        break;

                    case "deadline":
                        if (!arguments.contains(" /by ")) {
                            throw new HaruException.InvalidDeadlineException();
                        }
                        String[] deadlineArg = arguments.split(" /by ", 2);
                        String deadlineDesc = deadlineArg[0];
                        String by = deadlineArg[1];
                        deadline(tasks, deadlineDesc, by);
                        break;

                    case "event":
                        if (!arguments.contains(" /from ") || !arguments.contains(" /to ") ||
                                arguments.lastIndexOf(" /to ") < arguments.lastIndexOf(" /from ")) {
                            throw new HaruException.InvalidEventException();
                        }
                        String[] eventArg = arguments.split(" /from ", 2);
                        String eventDesc = eventArg[0];
                        String dates = eventArg[1];
                        String[] datesArray = dates.split(" /to ", 2);
                        String startDateTime = datesArray[0];
                        String endDateTime = datesArray[1];
                        event(tasks, eventDesc, startDateTime, endDateTime);
                        break;

                    case "delete":
                        if (arguments.isEmpty() || !arguments.matches("\\d+")) {
                            throw new HaruException.NumberFormatException();
                        }
                        taskIndex = Integer.parseInt(arguments) - 1;
                        if (taskIndex >= tasks.size()) {
                            throw new HaruException.InvalidIndexException();
                        }
                        delete(tasks, taskIndex);
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

    public static void greet() {
        System.out.println("""
        ____________________________________________________________
        Hello! I'm Haru
        What can I do for you?
        ____________________________________________________________
        """);
    }

    public static void bye() {
        System.out.println("""
        ____________________________________________________________
        Bye. Hope to see you again soon!
        ____________________________________________________________
        """);
    }

    public static void list(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks that you've set:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getTaskInfo());
        }
        System.out.println(LINEN);
    }

    public static void mark(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex).markDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).getTaskInfo());
        System.out.println(LINEN);    }

    public static void unmark(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex).markUndone();
        System.out.println(LINE);
        System.out.println("Got it! I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).getTaskInfo());
        System.out.println(LINEN);
    }

    public static void toDo(ArrayList<Task> tasks, String taskDescription) {
        tasks.add(new Todo(taskDescription));
        displayTask(tasks, tasks.size() - 1);
    }

    public static void deadline(ArrayList<Task> tasks, String description, String by) {
        tasks.add(new Deadline(description, by));
        displayTask(tasks, tasks.size() - 1);
    }

    public static void event(ArrayList<Task> tasks, String description, String startDateTime, String endDateTime) {
        tasks.add(new Event(description, startDateTime, endDateTime));
        displayTask(tasks, tasks.size() - 1);
    }

    public static void delete(ArrayList<Task> tasks, int taskIndex) {
        String taskInfo = tasks.get(taskIndex).getTaskInfo();
        tasks.remove(taskIndex);
        System.out.println(LINE);
        System.out.println("Understood! I've removed this task:\n" + taskInfo);
        System.out.println("There are now " + tasks.size() + " task(s)!");
        System.out.println(LINEN);
    }

    public static void displayTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(taskIndex).getTaskInfo());
        System.out.println("There are now " + tasks.size() + " task(s)!");
        System.out.println(LINEN);
    }
}