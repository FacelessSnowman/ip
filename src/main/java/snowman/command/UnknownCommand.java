package snowman.command;

import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command for handling unrecognized or unknown user input
 * in the Snowman application.
 * <p>
 * When executed, it displays a message indicating that the command
 * is not recognized and suggests typing 'help' to see available commands.
 * This prevents the program from crashing on invalid input and provides
 * user-friendly feedback.
 */
public class UnknownCommand extends Command {

    private final String input;

    public UnknownCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the UnknownCommand by displaying a message to the user
     * indicating that the command is not recognized and suggesting
     * to type 'help' for a list of valid commands.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Unknown command: " + input + "\n"
                + "Type 'help' to see the list of commands.";
        ui.showMessage(message);
        feedback = message;
    }
}
