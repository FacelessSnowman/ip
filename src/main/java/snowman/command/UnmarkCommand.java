package ip.src.main.java.snowman.command;

import ip.src.main.java.snowman.SnowmanException;
import ip.src.main.java.snowman.storage.Storage;
import ip.src.main.java.snowman.task.TaskList;
import ip.src.main.java.snowman.task.Task;
import ip.src.main.java.snowman.ui.Ui;

public class UnmarkCommand extends Command {
    private final String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = tasks.get(index);
            task.unmark();
            storage.save(tasks.getTasks());

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException  e) {
            throw new SnowmanException("Task number out of range.");
        }
    }
}
