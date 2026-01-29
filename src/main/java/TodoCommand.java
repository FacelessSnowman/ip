package ip.src.main.java;

public class TodoCommand extends Command {
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String inputSegment = input.length() > 4 ? input.substring(5).trim() : "";
        if (inputSegment.isEmpty()) {
            throw new SnowmanException("The description of a todo cannot be empty.");
        }

        Task task = new Todo(inputSegment);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
