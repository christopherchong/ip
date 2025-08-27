package haru.command;

import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
