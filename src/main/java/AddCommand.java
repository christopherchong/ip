import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException, IOException {
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.updateTaskList(tasks);
    }
}
