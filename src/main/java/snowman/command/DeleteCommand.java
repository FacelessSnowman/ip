package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.task.Task;
import snowman.ui.Ui;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String arg = input.length() > 6 ? input.substring(7).trim() : "";
        if (arg.isEmpty()) {
            throw new SnowmanException("Error: Task number to delete cannot be empty.");
        }

        try {
            int index = Integer.parseInt(arg) - 1;
            Task task = tasks.remove(index);
            ui.showDeletedTask(task, tasks.size());
            storage.save(tasks.getTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SnowmanException("Error: Invalid task number to delete.");
        }
    }
}
