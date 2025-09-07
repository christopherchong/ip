package haru.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Gui;

class TagCommandTest {
    private TaskList tasks;
    private Gui gui;
    private Storage storage;

    @BeforeEach
    void setUp() throws Exception {
        gui = new Gui();
        Path tempFile = Files.createTempFile("haru_test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write("T|0|read book|#test|\n");
            writer.write("T|0|write report| |\n");
        }
        storage = new Storage(tempFile);
        tasks = new TaskList(storage.loadTaskList());
    }

    @Test
    void execute_tagsTaskSuccessfully() throws HaruException, IOException {
        TagCommand tag = new TagCommand(1, "#fun");
        tag.execute(tasks, gui, storage);
        assertTrue(tasks.get(1).getTags().contains("#fun"), "Task should be tagged #fun");
    }

    @Test
    void execute_throwsInvalidExceptionIfInvalidIndex() {
        TagCommand tag = new TagCommand(3, "#fun");
        assertThrows(HaruException.InvalidIndexException.class, () -> tag.execute(tasks, gui, storage));
    }

    @Test
    void execute_throwsExistingTagExceptionIfAddingExistingTag() {
        TagCommand tag = new TagCommand(0, "#test");
        assertThrows(HaruException.ExistingTagException.class, () -> tag.execute(tasks, gui, storage));
    }
}
