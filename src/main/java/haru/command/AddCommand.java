package haru.command;

import java.io.IOException;

import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.updateTaskList(tasks);
    }
}
