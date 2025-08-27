package haru.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }

    public Todo(boolean isDone, String description) {
        super(description, isDone, 'T');
    }
}
