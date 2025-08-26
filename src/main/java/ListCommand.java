public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException {
        if (tasks.isEmpty()) {
            throw new HaruException.NoTasksException();
        }
        ui.showAllTasks(tasks);
    }
}