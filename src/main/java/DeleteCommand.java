public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HaruException {
        if (index >= tasks.size()) {
            throw new HaruException.InvalidIndexException();
        }

        String taskInfo = tasks.get(index).getTaskInfo();
        tasks.remove(index);
        ui.showDeletedTask(taskInfo, tasks.size());
    }
}
