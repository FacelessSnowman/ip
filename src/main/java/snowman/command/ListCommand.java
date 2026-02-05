package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to display all tasks in the Snowman application.
 * When executed, it shows the current task list to the user via the UI.
 */
public class ListCommand extends Command {
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
