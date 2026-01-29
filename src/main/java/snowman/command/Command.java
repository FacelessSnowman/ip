package ip.src.main.java.snowman.command;

import ip.src.main.java.snowman.SnowmanException;
import ip.src.main.java.snowman.storage.Storage;
import ip.src.main.java.snowman.task.TaskList;
import ip.src.main.java.snowman.ui.Ui;

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
