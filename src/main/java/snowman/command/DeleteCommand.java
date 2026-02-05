package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to delete a task in the Snowman application.
 * When executed, it removes the specified task from the task list,
 * updates storage, and displays a confirmation message.
 */
public class DeleteCommand extends Command {

    /** Full user input string for this command. */
    private final String input;

    /**
     * Constructs a DeleteCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
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
            storage.save(tasks.getTasks());
            // Build message for both console and GUI
            String message = "Noted. I've removed this task:\n"
                    + "  " + task + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";

            // Console output
            ui.showMessage(message);

            // GUI output
            feedback = message;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SnowmanException("Error: Invalid task number to delete.");
        }
    }
}
