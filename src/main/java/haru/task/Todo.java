package haru.task;

/**
 * Represents a {@code Todo} task without a specific due date/time.
 */
public class Todo extends Task {
    /**
     * Creates a new {@link Todo} task marked as not done.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, 'T');
    }

    /**
     * Creates a new {@link Todo} task with a given completion status.
     *
     * @param isDone Whether the task is completed.
     * @param description The description of the task.
     */
    public Todo(boolean isDone, String description) {
        super(description, isDone, 'T');
    }
}
