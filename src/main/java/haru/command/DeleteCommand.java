package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Represents a command that deletes a task in the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task in the task list to be deleted. */
    private final int index;

    /**
     * Creates a new {@code DeleteCommand} with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException {
        validateIndex(tasks, index);
        Task task = tasks.get(index);
        assert task != null : "Task to delete should not be null";
        String taskInfo = task.getTaskInfo();
        tasks.remove(index);
        storage.updateTaskList(tasks);
        return gui.showDeletedTask(taskInfo, tasks.size());
    }

    private static void validateIndex(TaskList tasks, int index) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
    }
}
