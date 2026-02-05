package snowman.command;

import java.util.ArrayList;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword
 * in their description in the Snowman application.
 */
public class FindCommand extends Command {

    /** Full user input string for this command. */
    private final String input;

    /**
     * Constructs a FindCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String keyword = input.length() > 4 ? input.substring(5).trim() : "";
        if (keyword.isEmpty()) {
            throw new SnowmanException("The keyword for find cannot be empty.");
        }

        ArrayList<Task> containKeywords = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                containKeywords.add(task);
            }
        }
        String message;
        if (containKeywords.isEmpty()) {
            message = "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < containKeywords.size(); i++) {
                sb.append((i + 1)).append(".").append(containKeywords.get(i)).append("\n");
            }
            message = sb.toString().trim();
            // For console mode
            ui.showMessage(message);
            // For GUI mode
            feedback = message;
        }
    }
}
