public class Task {
    protected String description;
    protected boolean status;
    protected char type;

    public Task(String description, char type) {
        this.description = description;
        this.status = false;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public char getType() {
        return type;
    }

    public String getStatus() {
        return (status ? "X" : " ");
    }

    public String getTaskInfo() {
        return "[" + this.type + "] [" + this.getStatus() + "] " + this.description;
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }
}