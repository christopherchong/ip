package haru.task;

import java.time.LocalDateTime;

import haru.util.DateTimeUtil;

/**
 * Represents an {@code Event} task with a start and end date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new {@link Event} task marked as not done.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, 'E');
        assert from != null && to != null : "from or to should not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new {@link Event} task with a given completion status.
     *
     * @param isDone Whether the event is completed.
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(description, isDone, 'E');
        assert from != null && to != null : "from or to should not be null";
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo()
                + " (from: " + DateTimeUtil.formatForDisplay(from)
                + " to: " + DateTimeUtil.formatForDisplay(to)
                + ")";
    }

    @Override
    public String getTaskInfoForFile() {
        return super.getTaskInfoForFile()
                + "|" + DateTimeUtil.formatForStorage(from)
                + "|" + DateTimeUtil.formatForStorage(to);
    }
}
