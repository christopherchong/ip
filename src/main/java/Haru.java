import java.util.Scanner;

public class Haru {
    public static void main(String[] args) {
        final int MAX_TASKS = 100;
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
        Scanner sc = new Scanner(System.in);

        greet();

        while (true) {
            String input = sc.nextLine();
            String command;
            String arguments = "";

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
                    list(tasks);
                    break;
                case "mark":
                    mark(tasks, arguments);
                    break;
                case "unmark":
                    unmark(tasks, arguments);
                    break;
                case "todo":
                    toDo(tasks, taskCount, arguments);
                    taskCount++;
                    break;
                case "deadline":
                    String[] deadlineArg = arguments.split(" /by ", 2);
                    String deadlineDesc = deadlineArg[0];
                    String by = deadlineArg[1];
                    deadline(tasks, taskCount, deadlineDesc, by);
                    taskCount++;
                    break;
                case "event":
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
            }
        }
    }

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Haru\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    public static void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void list(Task[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks that you've set:");
        int i = 0;
        while (tasks[i] != null) {
            System.out.println((i+1) + ". " + tasks[i].getTaskInfo());
            i++;
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void mark(Task[] tasks, String taskNo) {
        int taskIndex = Integer.parseInt(taskNo) - 1;
        tasks[taskIndex].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex].getTaskInfo());
        System.out.println("____________________________________________________________\n");
    }

    public static void unmark(Task[] tasks, String number) {
        int taskIndex = Integer.parseInt(number) - 1;
        tasks[taskIndex].markUndone();
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've marked this task as not done yet:");
        System.out.println(tasks[taskIndex].getTaskInfo());
        System.out.println("____________________________________________________________\n");
    }

    public static void toDo(Task[] tasks, int taskCount, String taskDescription) {
        tasks[taskCount] = new Todo(taskDescription);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].getTaskInfo());
        System.out.println("There are now " + (taskCount+1) + " task(s)!");
        System.out.println("____________________________________________________________\n");
    }

    public static void deadline(Task[] tasks, int taskCount, String description, String by) {
        tasks[taskCount] = new Deadline(description, by);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].getTaskInfo());
        System.out.println("There are now " + (taskCount+1) + " task(s)!");
        System.out.println("____________________________________________________________\n");
    }

    public static void event(Task[] tasks, int taskCount, String description, String startDateTime, String endDateTime) {
        tasks[taskCount] = new Event(description, startDateTime, endDateTime);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].getTaskInfo());
        System.out.println("There are now " + (taskCount+1) + " task(s)!");
        System.out.println("____________________________________________________________\n");
    }
}