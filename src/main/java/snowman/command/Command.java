package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Abstract Command class
 */
public abstract class Command {

    /** Execute this command using the task list, UI, and Storage */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException;

    /** Whether this command exits the program */
    public boolean isExit() {
        return false;
    }
}
