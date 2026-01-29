package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command in the Snowman application.
 * All commands must extend this class and implement the execute method.
 * Provides a default implementation of {@link #isExit()} to indicate if the command exits the application.
 */
public abstract class Command {

    /**
     * Executes this command using the given task list, UI, and storage.
     * Subclasses must implement this method to define their behavior.
     *
     * @param tasks TaskList object managing all tasks.
     * @param ui Ui object for displaying messages to the user.
     * @param storage Storage object for saving or loading tasks from file.
     * @throws SnowmanException If the command cannot be executed due to invalid input or other errors.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException;

    /**
     * Indicates whether this command signals the application to exit.
     * By default, commands do not exit the application.
     *
     * @return true if this command exits the application; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
