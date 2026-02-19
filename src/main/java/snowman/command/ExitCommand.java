package snowman.command;

import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command that exits the Snowman application.
 * Displays a farewell message and signals application termination.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");

        feedback = "Bye. Hope to see you again soon!";
    }

    /**
     * {@inheritDoc}
     *
     * @return true to indicate that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
