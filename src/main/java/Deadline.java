import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, 'D');
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(description, isDone, 'D');
        this.by = by;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    @Override
    public String getTaskInfoForFile() {
        return super.getTaskInfoForFile() + "|" + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}