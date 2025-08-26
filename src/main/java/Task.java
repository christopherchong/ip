public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public Task(String description, boolean isDone,char type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getTaskInfo() {
        return "[" + this.type + "] [" + this.getStatus() + "] " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
}