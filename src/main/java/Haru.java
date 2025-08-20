import java.util.Scanner;

public class Haru {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_TASKS = 100;
        String[] tasks = new String[MAX_TASKS];
        int task_count = 0;

        // Introduction
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Haru\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                int i = 0;
                while (tasks[i] != null) {
                    System.out.println((i+1) + ". " + tasks[i]);
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
            else {
                tasks[task_count++] = input;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________\n");
            }
        }
    }
}