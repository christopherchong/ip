public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
        Task task = tasks.get(index);
        if (task.getStatus().equals(" ")) {
            throw new HaruException.UnmarkException();
        }
        task.markUndone();
        ui.showUnmarkMessage(task);
    }
}