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
public class MarkCommand extends Command {
    /** Index of the task in the task list to be marked. */
    private final int index;

    /**
     * Creates a new {@code MarkCommand} with the index of the task to be marked.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException {
        validateIndex(tasks, index);
        Task task = tasks.get(index);
        assert task != null : "Task to mark should not be null";
        validateStatus(task);
        task.markDone();
        storage.updateTaskList(tasks);
        return gui.showMarkMessage(task);
    }

    private static void validateIndex(TaskList tasks, int index) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
    }

    private static void validateStatus(Task task) throws HaruException {
        if (task.getStatus().equals("X")) {
            throw new HaruException.MarkException();
        }
    }
}
