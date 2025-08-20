public class Task {
    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return (status ? "X" : " ");
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }
}
