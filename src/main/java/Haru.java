import java.util.Scanner;

public class Haru {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_TASKS = 100;
        Task[] tasks = new Task[MAX_TASKS];
        int task_count = 0;

        // Introduction
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Haru\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks that you've set:");
                int i = 0;
                while (tasks[i] != null) {
                    System.out.println((i+1) + ". [" + tasks[i].getStatus() + "] " + tasks[i].getDescription());
                    i++;
                }
                System.out.println("____________________________________________________________\n");
            }
            else if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else if (input.startsWith("mark ")) {
                int task_no = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[task_no].markDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[task_no].getStatus() + "] " + tasks[task_no].getDescription());
                System.out.println("____________________________________________________________\n");
            }
            else if (input.startsWith("unmark ")) {
                int task_no = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[task_no].markUndone();
                System.out.println("____________________________________________________________");
                System.out.println("Got it! I've marked this task as not done yet:");
                System.out.println("[" + tasks[task_no].getStatus() + "] " + tasks[task_no].getDescription());
                System.out.println("____________________________________________________________\n");
            }
            else {
                tasks[task_count++] = new Task(input);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}