package haru.task;

/**
 * Represents a generic task with a description, type, and completion status.
 * <p>
 * Base class for the following task types: {@link Todo}, {@link Deadline}, and {@link Event}.
 * </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Creates a new {@code Task} with the given description and type.
     * By default, the task is marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of the task. (e.g., 'T', 'D', 'E')
     */
    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Creates a new {@code Task} with the given description, type, and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     * @param type The type of the task. (e.g., 'T', 'D', 'E')
     */
    public Task(String description, boolean isDone,char type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /** @return {@code 'X'} if the task is done; {@code ' '} otherwise. */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /** @return A formatted string with task type, completion status, and description. */
    public String getTaskInfo() {
        return "[" + type + "] [" + getStatus() + "] " + description;
    }

    /** Marks the task as completed. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks the task as not completed. */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string of the task to be saved to the task file.
     *
     * @return Information of the task in storage format.
     */
    public String getTaskInfoForFile() {
        return type + "|" + (isDone ? "1" : "0") + "|" + description;
    }
}