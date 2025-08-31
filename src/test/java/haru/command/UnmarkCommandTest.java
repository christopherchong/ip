package haru.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

class UnmarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws Exception {
        ui = new Ui();
        Path tempFile = Files.createTempFile("haru_test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write("T|1|read book\n");
            writer.write("T|1|write report\n");
        }
        storage = new Storage(tempFile);
        tasks = new TaskList(storage.loadTaskList());
    }

    @Test
    void execute_marksTaskSuccessfully() throws HaruException, IOException {
        UnmarkCommand unmark = new UnmarkCommand(0);
        unmark.execute(tasks, ui, storage);
        assertEquals(" ", tasks.get(0).getStatus(), "Task should be unmarked");
    }

    @Test
    void execute_throwsUnmarkExceptionIfAlreadyUnmarked() {
        tasks.get(0).markUndone();
        UnmarkCommand unmark = new UnmarkCommand(0);
        assertThrows(HaruException.UnmarkException.class, () -> unmark.execute(tasks, ui, storage));
    }

    @Test
    void execute_throwsInvalidIndexExceptionIfIndexOutOfBounds() {
        UnmarkCommand unmark = new UnmarkCommand(5);
        assertThrows(HaruException.InvalidIndexException.class, () -> unmark.execute(tasks, ui, storage));
    }
}
