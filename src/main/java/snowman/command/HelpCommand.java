package snowman.command;

import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to display the list of available commands
 * and their usage instructions in the Snowman application.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand.
     * No input is needed since it always shows the same help message.
     */
    public HelpCommand() {
        // No additional fields needed
    }

    /**
     * Executes the HelpCommand by displaying all available commands
     * and their usage to the user.
     *
     * @param tasks   The TaskList containing current tasks (not used in this command).
     * @param ui      The Ui instance to display messages to the user.
     * @param storage The Storage instance used to persist tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = """
                Snowman Help - List of available commands:

                list
                  - Shows all tasks in your list.
                todo <description>
                  - Adds a todo task.
                deadline <description> /by <date/time>
                  - Adds a deadline task.
                event <description> /from <start> /to <end>
                  - Adds an event task.
                mark <task number>
                  - Marks a task as done.
                unmark <task number>
                  - Marks a task as not done.
                delete <task number>
                  - Deletes a task from the list.
                find <keyword>
                  - Finds tasks containing the keyword.
                help
                  - Shows this help message.
                bye
                  - Exits the application.
                """;

        ui.showMessage(message);
        feedback = message;
    }
}
