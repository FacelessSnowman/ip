package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.task.Deadline;
import snowman.task.Task;
import snowman.ui.Ui;

public class DeadlineCommand extends Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String[] inputSegment = input.length() > 8 ? input.substring(9).split(" /by ", 2) : new String[0];
        if (inputSegment.length < 2 || inputSegment[0].trim().isEmpty() || inputSegment[1].trim().isEmpty()) {
            throw new SnowmanException("The description or /by of a deadline cannot be empty.");
        }

        Task task = new Deadline(inputSegment[0], inputSegment[1]);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
