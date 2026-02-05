package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Deadline;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to add a Deadline task in the Snowman application.
 * When executed, it creates a Deadline with a description and due date,
 * adds it to the task list, updates storage, and shows a confirmation message.
 */
public class DeadlineCommand extends Command {

    /** Full user input string for this command. */
    private final String input;

    /**
     * Constructs a DeadlineCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String[] inputSegment = input.length() > 8 ? input.substring(9).split(" /by ", 2) : new String[0];
        if (inputSegment.length < 2 || inputSegment[0].trim().isEmpty() || inputSegment[1].trim().isEmpty()) {
            throw new SnowmanException("The description or /by of a deadline cannot be empty.");
        }

        Task task = new Deadline(inputSegment[0], inputSegment[1]);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());

        // prepare GUI feedback
        feedback = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
