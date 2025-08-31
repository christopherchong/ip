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

class MarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws Exception {
        ui = new Ui();
        Path tempFile = Files.createTempFile("haru_test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write("T|0|read book\n");
            writer.write("T|0|write report\n");
        }
        storage = new Storage(tempFile);
        tasks = new TaskList(storage.loadTaskList());
    }

    @Test
    void execute_marksTaskSuccessfully() throws HaruException, IOException {
        MarkCommand mark = new MarkCommand(0);
        mark.execute(tasks, ui, storage);
        assertEquals("X", tasks.get(0).getStatus(), "Task should be marked done");
    }

    @Test
    void execute_throwsMarkExceptionIfAlreadyMarked() {
        tasks.get(0).markDone();
        MarkCommand mark = new MarkCommand(0);
        assertThrows(HaruException.MarkException.class, () -> mark.execute(tasks, ui, storage));
    }

    @Test
    void execute_throwsInvalidIndexExceptionIfIndexOutOfBounds() {
        MarkCommand mark = new MarkCommand(5);
        assertThrows(HaruException.InvalidIndexException.class, () -> mark.execute(tasks, ui, storage));
    }
}
