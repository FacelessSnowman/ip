package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to mark a task as done in the Snowman application.
 * When executed, it marks the specified task as completed,
 * updates the task list in storage, and displays a confirmation message.
 */
public class MarkCommand extends Command {
    /** Full user input string for this command. */
    private final String input;

    /**
     * Constructs a MarkCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
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

            String message = "Nice! I've marked this task as done:\n"
                    + "  " + task;

            // For console mode
            ui.showMessage(message);

            // For GUI mode
            feedback = message;
        } catch (IndexOutOfBoundsException e) {
            throw new SnowmanException("Task number out of range.");
        } catch (NumberFormatException e) {
            throw new SnowmanException("Please provide a valid task number.");
        }
    }
}
