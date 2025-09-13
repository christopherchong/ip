package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    /** Task to be added. */
    private final Task task;

    /**
     * Creates a new {@code AddCommand} with the specified task.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException {
        checkForDuplicateTask(tasks, task);
        tasks.add(task);
        storage.updateTaskList(tasks);
        return gui.showAddedTask(task, tasks.size());
    }

    private static void checkForDuplicateTask(TaskList tasks, Task task) throws HaruException {
        String taskInfo = task.getTaskInfo();
        for (int i = 0; i < tasks.size(); i++) {
            String currentTaskInfo = tasks.get(i).getTaskInfo();
            if (currentTaskInfo.equals(taskInfo)) {
                throw new HaruException.DuplicateTaskException();
            }
        }
    }
}
