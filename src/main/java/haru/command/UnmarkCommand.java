package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Gui;

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
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException {
        validateIndex(tasks, index);
        Task task = tasks.get(index);
        validateStatus(task);
        task.markUndone();
        storage.updateTaskList(tasks);
        return gui.showUnmarkMessage(task);
    }

    private static void validateIndex(TaskList tasks, int index) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
    }

    private static void validateStatus(Task task) throws HaruException {
        if (task.getStatus().equals(" ")) {
            throw new HaruException.UnmarkException();
        }
    }
}
