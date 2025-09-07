package haru.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import haru.ui.Gui;

class UntagCommandTest {
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
            writer.write("T|0|write report|#test1 #test2 #test3|\n");
        }
        storage = new Storage(tempFile);
        tasks = new TaskList(storage.loadTaskList());
    }

    @Test
    void execute_tagsTaskSuccessfully() throws HaruException, IOException {
        UntagCommand untag = new UntagCommand(0, "#test");
        untag.execute(tasks, gui, storage);
        assertFalse(tasks.get(0).getTags().contains("#test"), "Task should not have #test tag");
    }

    @Test
    void execute_throwsInvalidExceptionIfInvalidIndex() {
        UntagCommand untag = new UntagCommand(4, "#fun");
        assertThrows(HaruException.InvalidIndexException.class, () -> untag.execute(tasks, gui, storage));
    }

    @Test
    void execute_throwsExistingTagExceptionIfAddingExistingTag() {
        UntagCommand untag = new UntagCommand(0, "#test1");
        assertThrows(HaruException.NoExistingTagException.class, () -> untag.execute(tasks, gui, storage));
    }
}
