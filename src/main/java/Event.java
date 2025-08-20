public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description, 'E');
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }
}