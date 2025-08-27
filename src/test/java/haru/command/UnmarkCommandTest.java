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

class UnmarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        tasks.add(new Todo(true, "read book"));
        tasks.add(new Todo(true, "write report"));
        ui = new Ui();
        storage = null;
    }

    @Test
    void execute_marksTaskSuccessfully() throws HaruException {
        UnmarkCommand unmark = new UnmarkCommand(0);
        unmark.execute(tasks, ui, storage);
        assertEquals(" ", tasks.get(0).getStatus(), "Task should be unmarked");
    }

    @Test
    void execute_throwsUnmarkExceptionIfAlreadyUnmarked() throws HaruException {
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
