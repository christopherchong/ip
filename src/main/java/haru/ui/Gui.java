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
        return "Hiya! I'm Haru Urara!\nWhat would you like me to do today?";
    }

    /**
     * Displays the exit message when the application ends.
     */
    public String showExitMessage() {
        return "Bye-bye! Can't wait to chat with you again soon!";
    }

    /**
     * Displays a confirmation message that a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public String showMarkMessage(Task task) {
        return "Yay! I've marked this task as done!\n"
                + task.getTaskInfo();
    }

    /**
     * Displays a confirmation message that a task was unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkMessage(Task task) {
        return "No worries! I've marked this task as not done yet\n"
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
        return "Here are all your tasks compiled!\n" + sb;
    }

    /**
     * Displays a confirmation message that a task has been added, along with the
     * updated number of tasks.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks in the task list after adding the new task.
     */
    public String showAddedTask(Task task, int taskCount) {
        return "Yippee! I've added this task:\n"
                + task.getTaskInfo() + "\n"
                + "Now you have " + taskCount + " task(s)! Let's get going!";
    }

    /**
     * Displays a confirmation message that a task has been deleted, along with the
     * updated number of tasks.
     *
     * @param taskInfo The information of the task that was deleted.
     * @param taskCount The number of tasks in the task list after deleting the task.
     */
    public String showDeletedTask(String taskInfo, int taskCount) {
        return "Got it! I've removed this task:\n"
                + taskInfo + "\n"
                + "You now have " + taskCount + " task(s). Keep it up!";
    }

    /**
     * Displays specified tasks in the task list to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String showSpecifiedTasks(StringBuilder taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("Oops! There were no tasks matching that description");
        } else {
            sb.append("Here are the tasks I found for you!\n");
            sb.append(taskList);
            sb.deleteCharAt(sb.lastIndexOf("\n"));
        }
        return sb.toString();
    }

    /**
     * Displays the task in the task list that was tagged to the user.
     *
     * @param task The tasks to be displayed.
     */
    public String showTagMessage(Task task, String tag) {
        return "All set! I've tagged this task with '" + tag + "'\n"
                + task.getTaskInfo();
    }

    /**
     * Displays the task in the task list that was untagged to the user.
     *
     * @param task The tasks to be displayed.
     */
    public String showUntagMessage(Task task, String tag) {
        return "No problemo! I've removed the '" + tag + "' tag\n"
                + task.getTaskInfo();
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
