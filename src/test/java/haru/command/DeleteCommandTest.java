package haru.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;

class DeleteCommandTest {
    private TaskList tasks;
    private Gui gui;
    private Storage storage;

    @BeforeEach
    void setUp() throws Exception {
        gui = new Gui();
        Path tempFile = Files.createTempFile("haru_test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write("T|0|read book| |\n");
        }
        storage = new Storage(tempFile);
        tasks = new TaskList(storage.loadTaskList());
    }

    @Test
    void execute_throwsInvalidIndexExceptionIfIndexOutOfBounds() {
        DeleteCommand delete = new DeleteCommand(3);
        assertThrows(HaruException.InvalidIndexException.class, () -> delete.execute(tasks, gui, storage));
    }
}
