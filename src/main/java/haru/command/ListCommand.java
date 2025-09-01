package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;
import haru.ui.Ui;

/**
 * Represents a command that lists the tasks in the task file.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }
        ui.showAllTasks(tasks);
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }
        return gui.showAllTasks(tasks);
    }
}
