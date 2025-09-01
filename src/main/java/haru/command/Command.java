package haru.command;

import java.io.IOException;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;
import haru.ui.Ui;

/**
 * Represents an abstract command that can be executed in the application.
 * <p>
 * Each {@code Command} defines a specific action that operates on the task list,
 * interacts with the user interface, and may update the task file.
 * Subclasses must implement the {@link #execute(TaskList, Ui, Storage)} method
 * to define their behavior.
 * </p>
 * <p>
 * By default, a command does not terminate the program unless {@link #isExit()} is overridden.
 * </p>
 */
public abstract class Command {
    /**
     * Executes a command with the given task list, user interface, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage handler to update the task file.
     * @throws HaruException If an error specific to command execution occurs.
     * @throws IOException If an I/O error occurs while accessing storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException;
    public abstract String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException;

    /**
     * Indicates whether this command will terminate the program.
     * By default, commands do not exit.
     *
     * @return {@code true} if this command should exit the program;
     *         {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
