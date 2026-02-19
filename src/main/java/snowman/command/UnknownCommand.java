package snowman.command;

import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command that handles unrecognized or unknown user input
 * in the Snowman application.
 * Executes by displaying a message indicating that the command
 * is not recognized and suggests typing 'help' to see available commands.
 */
public class UnknownCommand extends Command {

    private final String input;

    /**
     * Creates an UnknownCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public UnknownCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the unknown command by displaying a message to the user
     * indicating that the command is not recognized and suggesting
     * to type 'help' for a list of valid commands.
     *
     * @param tasks Task list (unused for this command).
     * @param ui User interface used to display feedback.
     * @param storage Storage used to persist tasks (unused for this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Unknown command: " + input + "\n"
                + "Type 'help' to see the list of commands.";
        ui.showMessage(message);
        feedback = message;
    }
}
