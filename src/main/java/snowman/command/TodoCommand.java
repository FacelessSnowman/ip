package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.task.Todo;
import snowman.ui.Ui;

/**
 * Represents a command that adds a Todo task to the Snowman application.
 * Executes the addition of a Todo task with a given description, updates storage,
 * and prepares user feedback.
 */
public class TodoCommand extends Command {
    /** Full user input string for this command. */
    private final String input;
    /**
     * Creates a TodoCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the todo command by parsing the input, creating a Todo task,
     * adding it to the task list, saving the updated list to storage,
     * and preparing feedback for the user.
     *
     * @param tasks Task list to which the new task is added.
     * @param ui User interface used to display feedback.
     * @param storage Storage used to persist tasks.
     * @throws SnowmanException If the description is missing or empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String inputSegment;

        if (input.length() > 4) {
            inputSegment = input.substring(5).trim();
        } else {
            inputSegment = "";
        }

        if (inputSegment.isEmpty()) {
            throw new SnowmanException("The description of a todo cannot be empty.");
        }

        Task task = new Todo(inputSegment);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());

        // prepare GUI feedback
        feedback = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";

        // Console mode
        ui.showMessage(feedback);
    }
}
