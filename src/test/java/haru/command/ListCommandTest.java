package haru.command;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.task.Todo;
import haru.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = null;
    }

    @Test
    void execute_throwsNoTasksExceptionIfEmptyTaskList() {
        ListCommand list = new ListCommand();
        assertThrows(HaruException.NoTasksException.class, () -> list.execute(tasks, ui, storage));
    }
}
