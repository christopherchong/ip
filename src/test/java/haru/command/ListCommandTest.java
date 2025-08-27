package haru.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.TaskList;
import haru.ui.Ui;

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
