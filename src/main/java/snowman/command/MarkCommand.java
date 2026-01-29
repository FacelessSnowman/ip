package ip.src.main.java.snowman.command;

import ip.src.main.java.snowman.SnowmanException;
import ip.src.main.java.snowman.storage.Storage;
import ip.src.main.java.snowman.task.TaskList;
import ip.src.main.java.snowman.task.Task;
import ip.src.main.java.snowman.ui.Ui;

public class MarkCommand extends Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;

            Task task = tasks.get(index);
            task.markAsDone();
            storage.save(tasks.getTasks());

            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException  e) {
            throw new SnowmanException("Task number out of range.");
        }
    }
}
