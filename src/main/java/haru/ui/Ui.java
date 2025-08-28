package haru.ui;

import java.util.Scanner;

import haru.task.Task;
import haru.task.TaskList;

/**
 * Handles all interactions with the user.
 *
 * <p>The {@code Ui} class is responsible for reading user input as well as
 * displaying messages to the user, such as greetings, errors, and updates to the task list.
 * </p>
 */
public class Ui {
    private static final String LINE =
            "___________________________________________________________________________________";
    private static final String LINEN =
            "___________________________________________________________________________________\n";

    /**
     * Reads a command input from the user.
     *
     * @return The user input as a {@code String} object
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Haru\nWhat can I do for you?");
        System.out.println(LINEN);
    }

    /**
     * Displays the exit message when the application ends.
     */
    public void showExitMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINEN);
    }

    /**
     * Displays a confirmation message that a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.getTaskInfo());
        System.out.println(LINEN);
    }

    /**
     * Displays a confirmation message that a task was unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it! I've marked this task as not done yet:");
        System.out.println("\t" + task.getTaskInfo());
        System.out.println(LINEN);
    }

    /**
     * Displays all tasks in the task list to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showAllTasks(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks that you've set:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).getTaskInfo());
        }
        System.out.println(LINEN);
    }


    /**
     * Displays a confirmation message that a task has been added, along with the
     * updated number of tasks.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks in the task list after adding the new task.
     */
    public void showAddedTask(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.getTaskInfo());
        System.out.println("There are now " + taskCount + " task(s)!");
        System.out.println(LINEN);
    }

    /**
     * Displays a confirmation message that a task has been deleted, along with the
     * updated number of tasks.
     *
     * @param taskInfo The information of the task that was deleted.
     * @param taskCount The number of tasks in the task list after deleting the task.
     */
    public void showDeletedTask(String taskInfo, int taskCount) {
        System.out.println(LINE);
        System.out.println("Understood! I've removed this task:\n\t" + taskInfo);
        System.out.println("There are now " + taskCount + " task(s)!");
        System.out.println(LINEN);
    }

    /**
     * Displays specified tasks in the task list to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(StringBuilder taskList) {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("There were no tasks matching the description!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.print(taskList);
        }
        System.out.println(LINEN);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
