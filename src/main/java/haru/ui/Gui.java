package haru.ui;

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
    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Haru\nWhat can I do for you?";
    }

    /**
     * Displays the exit message when the application ends.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a confirmation message that a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public String showMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.getTaskInfo();
    }

    /**
     * Displays a confirmation message that a task was unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkMessage(Task task) {
        return "Got it! I've marked this task as not done yet:\n"
                + task.getTaskInfo();
    }

    /**
     * Displays all tasks in the task list to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showAllTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            String currentTaskInfo = tasks.get(i).getTaskInfo();
            String task = (i + 1) + ". " + currentTaskInfo + "\n";
            sb.append(task);
        }
        sb.deleteCharAt(sb.lastIndexOf("\n"));
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
        return "Got it. I've added this task:\n"
                + task.getTaskInfo() + "\n"
                + "There are now " + taskCount + " task(s)!";
    }

    /**
     * Displays a confirmation message that a task has been deleted, along with the
     * updated number of tasks.
     *
     * @param taskInfo The information of the task that was deleted.
     * @param taskCount The number of tasks in the task list after deleting the task.
     */
    public String showDeletedTask(String taskInfo, int taskCount) {
        return "Understood! I've removed this task:\n"
                + taskInfo + "\n"
                + "There are now " + taskCount + " task(s)!";
    }

    /**
     * Displays specified tasks in the task list to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String showSpecifiedTasks(StringBuilder taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("There were no tasks matching the description!\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            sb.append(taskList);
            sb.deleteCharAt(sb.lastIndexOf("\n"));
        }
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
