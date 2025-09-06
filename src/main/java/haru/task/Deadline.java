package haru.task;

import java.time.LocalDateTime;

import haru.util.DateTimeUtil;

/**
 * Represents a {@code Deadline} task with a specified due date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new {@link Deadline} task marked as not done.
     *
     * @param description The description of the task.
     * @param by The due date/time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, 'D');
        this.by = by;
    }

    /**
     * Creates a new {@link Deadline} task with a given completion status.
     *
     * @param isDone Whether the task is completed.
     * @param description The description of the task.
     * @param by The due date/time of the task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(description, isDone, 'D');
        this.by = by;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }

    @Override
    public String getTaskInfoForFile() {
        return super.getTaskInfoForFile() + "|" + DateTimeUtil.formatForStorage(by);
    }
}
