package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.task.Todo;
import haru.ui.Ui;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Path tempFile;

    @BeforeEach
    void setUp() throws Exception {
        ui = new Ui();
        tempFile = Files.createTempFile("haru_test", ".txt");
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
    void execute_throwsMarkExceptionIfAlreadyMarked() throws HaruException {
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
