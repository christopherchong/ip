package haru.command;

import java.io.IOException;

import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

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
