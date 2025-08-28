package haru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an {@code Event} task with a start and end date/time.
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Creates a new {@link Event} task marked as not done.
     *
     * @param description The description of the event.
     * @param startDateTime The start date/time of the event.
     * @param endDateTime The end date/time of the event.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, 'E');
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Creates a new {@link Event} task with a given completion status.
     *
     * @param isDone Whether the event is completed.
     * @param description The description of the event.
     * @param startDateTime The start date/time of the event.
     * @param endDateTime The end date/time of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone, 'E');
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getTaskInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return super.getTaskInfo() + " (from: " + startDateTime.format(formatter)
                + " to: " + endDateTime.format(formatter) + ")";
    }

    @Override
    public String getTaskInfoForFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.getTaskInfoForFile() + "|" + startDateTime.format(formatter)
                + "|" + endDateTime.format(formatter);
    }
}
