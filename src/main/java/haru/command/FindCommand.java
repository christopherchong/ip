package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

public class FindCommand extends Command {
    private final String description;

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
                taskList.append("\t").append(i + 1).append(". ").append(tasks.get(i).getTaskInfo()).append("\n");
            }
        }
        ui.showTaskList(taskList);
    }
}