package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

public class DeleteCommand extends Command {
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
