package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Represents a command that finds tasks in the task list by a specific description.
 */
public class FindCommand extends Command {
    /** Description to find tasks in the task list by. */
    private final String description;

    /**
     * Creates a new {@code FindCommand} with a specified description.
     *
     * @param description The description of the task.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException {
        checkIfTaskListIsEmpty(tasks);
        StringBuilder taskList = new StringBuilder();
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            String currentTaskInfo = tasks.get(i).getTaskInfo();
            if (currentTaskInfo.contains(description)) {
                String task = (i + 1) + ". " + currentTaskInfo + "\n";
                taskList.append(task);
            }
        }
        return gui.showSpecifiedTasks(taskList);
    }

    private static void checkIfTaskListIsEmpty(TaskList tasks) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }
    }
}
