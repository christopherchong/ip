import java.util.ArrayList;

public class Ui {
    private static final String LINE = "___________________________________________________________________________________";
    private static final String LINEN = "___________________________________________________________________________________\n";

    public void showWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Haru\nWhat can I do for you?");
        System.out.println(LINEN);
    }

    public void showExitMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINEN);
    }

    public void showMarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.getTaskInfo());
        System.out.println(LINEN);
    }

    public void showUnmarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it! I've marked this task as not done yet:");
        System.out.println("\t" + task.getTaskInfo());
        System.out.println(LINEN);
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks that you've set:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).getTaskInfo());
        }
        System.out.println(LINEN);
    }

    public void showAddedTask(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.getTaskInfo());
        System.out.println("There are now " + taskCount + " task(s)!");
        System.out.println(LINEN);
    }

    public void showDeletedTask(String taskInfo,  int taskCount) {
        System.out.println(LINE);
        System.out.println("Understood! I've removed this task:\n\t" + taskInfo);
        System.out.println("There are now " + taskCount + " task(s)!");
        System.out.println(LINEN);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
