import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Haru {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Haru(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            storage.verifyTaskFile();
            tasks = new TaskList(storage.loadTaskList());
        } catch (HaruException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(); // overwrites corrupted haru.txt
        }
    }

    public void run() {
        ui.showWelcomeMessage();
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

    public static void main(String[] args) {
        Path filePath = Paths.get("src","data", "haru.txt");
        new Haru(filePath).run();
    }
}