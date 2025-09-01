package haru.ui;

import java.util.Scanner;

import haru.task.Task;
import haru.task.TaskList;

/**
 * Handles all interactions with the user in the GUI version.
 *
 * <p>The {@code Gui} class is responsible for reading user input as well as
 * displaying messages to the user, such as greetings, errors, and updates to the task list.
 * </p>
 */
public class Gui {
    private static final String LINE =
            "________________________________________";
    private static final String LINEN =
            "________________________________________\n";
    private final Scanner sc;

    public Gui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user input as a {@code String} object
     */
    public String readCommand() {
        return sc.hasNextLine() ? sc.nextLine() : "";
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcomeMessage() {
        return LINE + "\n"
                + "Hello! I'm Haru\nWhat can I do for you?\n"
                + LINEN;
    }

    /**
     * Displays the exit message when the application ends.
     */
    public String showExitMessage() {
        return LINE + "\n"
                + "Bye. Hope to see you again soon!\n"
                + LINEN;
    }

    /**
     * Displays a confirmation message that a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public String showMarkMessage(Task task) {
        return LINE + "\n"
                + "Nice! I've marked this task as done:\n"
                + "\t" + task.getTaskInfo() + "\n"
                + LINEN;
    }

    /**
     * Displays a confirmation message that a task was unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkMessage(Task task) {
        return LINE + "\n"
                + "Got it! I've marked this task as not done yet:\n"
                + "\t" + task.getTaskInfo() + "\n"
                + LINEN;
    }

    /**
     * Displays all tasks in the task list to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showAllTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        sb.append("Here are the tasks that you've set:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t").append(i + 1).append(". ").append(tasks.get(i).getTaskInfo()).append("\n");
        }
        sb.append(LINEN);
        return sb.toString();
    }


    /**
     * Displays a confirmation message that a task has been added, along with the
     * updated number of tasks.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks in the task list after adding the new task.
     */
    public String showAddedTask(Task task, int taskCount) {
        return LINE + "\n"
                + "Got it. I've added this task:\n"
                + "\t" + task.getTaskInfo() + "\n"
                + "There are now " + taskCount + " task(s)!\n"
                + LINEN;
    }

    /**
     * Displays a confirmation message that a task has been deleted, along with the
     * updated number of tasks.
     *
     * @param taskInfo The information of the task that was deleted.
     * @param taskCount The number of tasks in the task list after deleting the task.
     */
    public String showDeletedTask(String taskInfo, int taskCount) {
        return LINE + "\n"
                + "Understood! I've removed this task:\n\t" + taskInfo + "\n"
                + "There are now " + taskCount + " task(s)!\n"
                + LINEN;
    }

    /**
     * Displays specified tasks in the task list to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String showTaskList(StringBuilder taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        if (taskList.isEmpty()) {
            sb.append("There were no tasks matching the description!\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            sb.append(taskList);
        }
        sb.append(LINEN);
        return sb.toString();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }
}
