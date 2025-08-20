import java.util.Scanner;

public class Haru {
    public static void main(String[] args) {
        final int MAX_TASKS = 100;
        Task[] tasks = new Task[MAX_TASKS];
        int task_count = 0;
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
                case "bye":
                    bye();
                    System.exit(0);
                default:
                    addTask(tasks, task_count, input);
                    task_count++;
                    break;
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
            System.out.println((i+1) + ". [" + tasks[i].getStatus() + "] " + tasks[i].getDescription());
            i++;
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void mark(Task[] tasks, String number) {
        int taskNo = Integer.parseInt(number) - 1;
        tasks[taskNo].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + tasks[taskNo].getStatus() + "] " + tasks[taskNo].getDescription());
        System.out.println("____________________________________________________________\n");
    }

    public static void unmark(Task[] tasks, String number) {
        int taskNo = Integer.parseInt(number) - 1;
        tasks[taskNo].markUndone();
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've marked this task as not done yet:");
        System.out.println("[" + tasks[taskNo].getStatus() + "] " + tasks[taskNo].getDescription());
        System.out.println("____________________________________________________________\n");
    }

    public static void addTask(Task[] tasks, int task_count, String input) {
        tasks[task_count] = new Task(input);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________\n");
    }
}