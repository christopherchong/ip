package haru;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import haru.command.Command;
import haru.parser.Parser;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;
import haru.ui.Ui;

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
    private final Ui ui;
    private final Gui gui;

    /**
     * Creates a new Haru instance with the given file path.
     *
     * @param filePath The file path to the task file.
     */
    public Haru(Path filePath) {
        ui = new Ui();
        gui = new Gui();
        storage = new Storage(filePath);

        try {
            storage.verifyTaskFile();
            tasks = new TaskList(storage.loadTaskList());
        } catch (HaruException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(); // overwrites corrupted haru.txt
        }
    }

    /**
     * Starts the main program loop for Haru (in the Text UI).
     * Displays the welcome message, processes user commands,
     * and runs until the exit command is provided.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HaruException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Path filePath = Paths.get("src", "data", "haru.txt");
        new Haru(filePath).run();
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
