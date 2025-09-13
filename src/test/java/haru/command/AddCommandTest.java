package haru.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.task.Todo;
import haru.ui.Gui;

class AddCommandTest {
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
    void execute_throwsDuplicateTaskExceptionIfAddSameTask() {
        Task task = new Todo("read book");
        AddCommand add = new AddCommand(task);
        assertThrows(HaruException.DuplicateTaskException.class, () -> add.execute(tasks, gui, storage));
    }
}
