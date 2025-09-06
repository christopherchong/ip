package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Represents a command that lists the tasks in the task file.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException {
        checkIfTaskListIsEmpty(tasks);
        return gui.showAllTasks(tasks);
    }

    private static void checkIfTaskListIsEmpty(TaskList tasks) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }
    }
}
