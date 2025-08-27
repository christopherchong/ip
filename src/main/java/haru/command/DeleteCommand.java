package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

/**
 * Represents a command that deletes a task in the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task in the task list to be deleted. */
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }

        String taskInfo = tasks.get(index).getTaskInfo();
        tasks.remove(index);
        ui.showDeletedTask(taskInfo, tasks.size());
        storage.updateTaskList(tasks);
    }
}
