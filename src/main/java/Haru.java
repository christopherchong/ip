import java.util.Scanner;

public class Haru {
    public static void main(String[] args) {
        final int MAX_TASKS = 100;
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
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
                        if (taskCount == 0) {
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
                        if (taskIndex >= taskCount) {
                            throw new HaruException.InvalidIndexException();
                        }

                        if (command.equals("mark")) {
                            if (tasks[taskIndex].getStatus().equals("X")) {
                                throw new HaruException.MarkException();
                            }
                            mark(tasks, taskIndex);
                        }
                        else {
                            if (tasks[taskIndex].getStatus().equals(" ")) {
                                throw new HaruException.UnmarkException();
                            }
                            unmark(tasks, taskIndex);
                        }
                        break;

                    case "todo":
                        if (arguments.isEmpty()) {
                            throw new HaruException.InvalidTodoException();
                        }
                        toDo(tasks, taskCount, arguments);
                        taskCount++;
                        break;

                    case "deadline":
                        if (!arguments.contains(" /by ")) {
                            throw new HaruException.InvalidDeadlineException();
                        }
                        String[] deadlineArg = arguments.split(" /by ", 2);
                        String deadlineDesc = deadlineArg[0];
                        String by = deadlineArg[1];
                        deadline(tasks, taskCount, deadlineDesc, by);
                        taskCount++;
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
                        event(tasks, taskCount, eventDesc, startDateTime, endDateTime);
                        taskCount++;
                        break;

                    case "bye":
                        bye();
                        System.exit(0);

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

    public static void list(Task[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks that you've set:");
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getTaskInfo());
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void mark(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex].getTaskInfo());
        System.out.println("____________________________________________________________\n");
    }

    public static void unmark(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markUndone();
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've marked this task as not done yet:");
        System.out.println(tasks[taskIndex].getTaskInfo());
        System.out.println("____________________________________________________________\n");
    }

    public static void toDo(Task[] tasks, int taskCount, String taskDescription) {
        tasks[taskCount] = new Todo(taskDescription);
        displayTask(tasks, taskCount);
    }

    public static void deadline(Task[] tasks, int taskCount, String description, String by) {
        tasks[taskCount] = new Deadline(description, by);
        displayTask(tasks, taskCount);
    }

    public static void event(Task[] tasks, int taskCount, String description, String startDateTime, String endDateTime) {
        tasks[taskCount] = new Event(description, startDateTime, endDateTime);
        displayTask(tasks, taskCount);
    }

    public static void displayTask(Task[] tasks, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].getTaskInfo());
        System.out.println("There are now " + (taskCount+1) + " task(s)!");
        System.out.println("____________________________________________________________\n");
    }
}