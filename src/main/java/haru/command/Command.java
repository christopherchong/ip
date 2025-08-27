package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException;
    public boolean isExit() {
        return false;
    }
}
