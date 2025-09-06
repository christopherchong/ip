package haru.command;

import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Represents a command that terminates the application.
 */
public class ExitCommand extends Command {
    public String execute(TaskList tasks, Gui gui, Storage storage) {
        return gui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
