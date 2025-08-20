public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + " (by: " + by + ")";
    }
}
