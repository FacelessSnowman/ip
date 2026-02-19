package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command that unmarks a task as not done in the Snowman application.
 * Executes by setting the specified task as uncompleted, updating storage,
 * and preparing user feedback.
 */
public class UnmarkCommand extends Command {
    private final String input;

    /**
     * Constructs an UnmarkCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the unmark command by parsing the task number from user input,
     * unmarking the corresponding task, saving the updated task list to storage,
     * and preparing feedback for console and GUI.
     *
     * @param tasks Task list containing the task to unmark.
     * @param ui User interface used to display feedback.
     * @param storage Storage used to persist tasks.
     * @throws SnowmanException If the task number is missing, invalid, or out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = tasks.get(index);
            task.unmark();
            storage.save(tasks.getTasks());

            String message = "OK, I've marked this task as not done yet:\n  " + task;

            // For console mode
            ui.showMessage(message);
            // For GUI mode
            feedback = message;
        } catch (IndexOutOfBoundsException e) {
            throw new SnowmanException("Please provide the number of the task to be unmarked.");
        } catch (NumberFormatException e) {
            throw new SnowmanException("Please provide the number as an integer.");
        }
    }
}
