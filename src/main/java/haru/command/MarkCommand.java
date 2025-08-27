package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Ui;

/**
 * Represents a command that marks a task in the task list.
 */
public class MarkCommand extends Command {
    /** Index of the task in the task list to be marked. */
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
        Task task = tasks.get(index);
        if (task.getStatus().equals("X")) {
            throw new HaruException.MarkException();
        }
        task.markDone();
        ui.showMarkMessage(task);
        storage.updateTaskList(tasks);
    }
}