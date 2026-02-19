package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command that marks a task as done in the Snowman application.
 * Executes by setting the specified task as completed, updating storage,
 * and preparing user feedback.
 */
public class MarkCommand extends Command {
    private final String input;

    /**
     * Constructs a MarkCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the mark command by parsing the task number from user input,
     * marking the corresponding task as done, saving the updated task list to storage,
     * and preparing feedback for console and GUI.
     *
     * @param tasks Task list containing the task to mark.
     * @param ui User interface used to display feedback.
     * @param storage Storage used to persist tasks.
     * @throws SnowmanException If the task number is missing, invalid, or out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            storage.save(tasks.getTasks());

            String message = "Nice! I've marked this task as done:\n  " + task;

            // For console mode
            ui.showMessage(message);
            // For GUI mode
            feedback = message;
        } catch (IndexOutOfBoundsException e) {
            throw new SnowmanException("Please provide the number of the task to be marked.");
        } catch (NumberFormatException e) {
            throw new SnowmanException("Please provide the number as an integer.");
        }
    }
}
