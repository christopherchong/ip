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
    protected String tags;

    /**
     * Creates a new {@code Task} with the given description and type.
     * By default, the task is marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of the task. (e.g., 'T', 'D', 'E')
     */
    public Task(String description, char type) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.tags = " ";
    }

    /**
     * Creates a new {@code Task} with the given description, type, and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     * @param type The type of the task. (e.g., 'T', 'D', 'E')
     */
    public Task(String description, boolean isDone, char type, String tags) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = isDone;
        this.type = type;
        this.tags = tags;
    }

    /** @return {@code 'X'} if the task is done; {@code ' '} otherwise. */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /** @return A formatted string with task type, completion status, and description. */
    public String getTaskInfo() {
        return "[" + type + "] [" + getStatus() + "] " + description + " " + tags;
    }

    /** @return Added tags for the current task. */
    public String getTags() {
        return tags;
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
     * Adds a new {@code tag} to the current task.
     *
     * @param tag The name of the tag.
     */
    public void tag(String tag) {
        if (this.tags.equals(" ")) {
            this.tags = tag;
        } else {
            this.tags += " " + tag;
        }
    }

    /**
     * Returns a formatted string of the task to be saved to the task file.
     *
     * @return Information of the task in storage format.
     */
    public String getTaskInfoForFile() {
        return type + "|" + (isDone ? "1" : "0") + "|" + description + "|" + tags;
    }
}
