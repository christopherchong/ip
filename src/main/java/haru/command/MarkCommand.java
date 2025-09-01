package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Gui;
import haru.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
        Task task = tasks.get(index);
        if (task.getStatus().equals("X")) {
            throw new HaruException.MarkException();
        }
        task.markDone();
        storage.updateTaskList(tasks);
        ui.showMarkMessage(task);
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
        Task task = tasks.get(index);
        if (task.getStatus().equals("X")) {
            throw new HaruException.MarkException();
        }
        task.markDone();
        storage.updateTaskList(tasks);
        return gui.showMarkMessage(task);
    }
}
