package haru.command;

import java.io.IOException;
import java.util.List;

import haru.HaruException;
import haru.storage.Storage;
import haru.task.Task;
import haru.task.TaskList;
import haru.ui.Gui;

/**
 * Represents a command that untags tasks in the task list by a specific tag.
 */
public class UntagCommand extends Command {
    /** Tag to untag tasks in the task list by. */
    private final String tag;
    /** Index of the task in the task list to be untagged. */
    private final int index;

    /**
     * Creates a new {@code UntagCommand} with a specified description.
     *
     * @param tag The description of the tag.
     * @param index The index of the task to be untagged.
     */
    public UntagCommand(int index, String tag) {
        assert tag != null && !tag.isBlank() : "Tag should not be null or blank";
        this.tag = tag;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws HaruException, IOException {
        checkIfTaskListIsEmpty(tasks);
        validateIndex(tasks, index);
        Task task = tasks.get(index);
        assert task != null : "Task to untag should not be null";
        validateTag(task, tag);
        task.untag(tag);
        storage.updateTaskList(tasks);
        return gui.showUntagMessage(task, tag);
    }

    private static void checkIfTaskListIsEmpty(TaskList tasks) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }
    }

    private static void validateIndex(TaskList tasks, int index) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
    }

    private static void validateTag(Task task, String tag) throws HaruException {
        List<String> tagList = List.of(task.getTags().split("\\s+"));
        if (!tagList.contains(tag)) {
            throw new HaruException.NoExistingTagException(tag);
        }
    }
}
