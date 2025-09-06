package haru;

import java.io.IOException;
import java.nio.file.Path;

import haru.command.Command;
import haru.parser.Parser;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Entry point of the Haru task manager application.
 * <p>
 * The {@code Haru} class initializes the user interface, storage,
 * and task list, and facilitates their interaction. It is responsible
 * for starting the application loop and handling user commands until exit.
 * </p>
 */
public class Haru {
    private final Storage storage;
    private TaskList tasks;
    private final Gui gui;

    /**
     * Creates a new Haru instance with the given file path.
     *
     * @param filePath The file path to the task file.
     */
    public Haru(Path filePath) {
        gui = new Gui();
        storage = new Storage(filePath);

        try {
            storage.verifyTaskFile();
            tasks = new TaskList(storage.loadTaskList());
        } catch (HaruException | IOException e) {
            gui.showError(e.getMessage());
            tasks = new TaskList(); // overwrites corrupted haru.txt
        }
    }

    /**
     * Displays welcome message upon starting the application.
     */
    public String showGreetings() {
        return gui.showWelcomeMessage();
    }

    /**
     * Parses user input into a command to retrieve the response.
     * Used in the GUI application.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, gui, storage);
        } catch (HaruException | IOException e) {
            response = gui.showError(e.getMessage());
        }

        return response;
    }
}
