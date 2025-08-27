package haru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, 'E');
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(boolean isDone, String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone, 'E');
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getTaskInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return super.getTaskInfo() + " (from: " + startDateTime.format(formatter) + " to: " + endDateTime.format(formatter) + ")";
    }

    @Override
    public String getTaskInfoForFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.getTaskInfoForFile() + "|" + startDateTime.format(formatter) + "|" + endDateTime.format(formatter);
    }
}