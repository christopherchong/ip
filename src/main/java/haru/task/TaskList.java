package haru.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks using an {@link ArrayList}.
 * <p>
 * Provides methods to add, remove, retrieve, and query tasks in the list.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a {@code TaskList} from an existing task list.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** @return The number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /**
     * @return {@code true} if the list contains no tasks;
     *         {@code false} otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index in the task list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to remove from the task list.
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
