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
public class UnmarkCommand extends Command {
    /** Index of the task in the task list to be unmarked. */
    private final int index;

    /**
     * Creates a new {@code UnmarkCommand} with the index of the task to be unmarked.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
        Task task = tasks.get(index);
        if (task.getStatus().equals(" ")) {
            throw new HaruException.UnmarkException();
        }
        task.markUndone();
        ui.showUnmarkMessage(task);
        storage.updateTaskList(tasks);
    }
}
