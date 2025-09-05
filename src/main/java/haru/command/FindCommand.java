package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;
import haru.ui.Ui;

/**
 * Represents a command that finds tasks in the task list by a specific description.
 */
public class FindCommand extends Command {
    /** Description to find tasks in the task list by. */
    private final String description;

    /**
     * Creates a new {@code FindCommand} with a specified description.
     *
     * @param description The description of the task.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }

        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskInfo().contains(description)) {
                String task = (i + 1) + ". " + tasks.get(i).getTaskInfo() + "\n";
                taskList.append(task);
            }
        }
        ui.showTaskList(taskList);
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }

        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskInfo().contains(description)) {
                String task = (i + 1) + ". " + tasks.get(i).getTaskInfo() + "\n";
                taskList.append(task);
            }
        }
        return gui.showTaskList(taskList);
    }
}
