package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.task.Todo;
import haru.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        tasks.add(new Todo(false, "read book"));
        tasks.add(new Todo(false, "write report"));
        ui = new Ui();
        storage = null;
    }

    @Test
    void execute_marksTaskSuccessfully() throws HaruException {
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
