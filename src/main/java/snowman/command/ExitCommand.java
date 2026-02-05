package snowman.command;

import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to exit the Snowman application.
 * When executed, it displays a goodbye message to the user.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");

        feedback = "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}