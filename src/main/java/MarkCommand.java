public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }
        Task task = tasks.get(index);
        if (task.getStatus().equals("X")) {
            throw new HaruException.MarkException();
        }
        task.markDone();
        ui.showMarkMessage(task);
    }
}