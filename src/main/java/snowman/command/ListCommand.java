package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command that displays all tasks in the Snowman application.
 * Executes the listing of all tasks and prepares feedback for the user.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * @param tasks Task list to be displayed.
     * @param ui User interface used to display feedback.
     * @param storage Storage used to persist tasks.
     * @throws SnowmanException Never thrown for this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        if (tasks.size() == 0) {
            feedback = "Your task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            feedback = sb.toString().trim();
        }

        // Console mode
        ui.showMessage(feedback);
    }
}
