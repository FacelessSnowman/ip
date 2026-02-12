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

        // Parse the input
        String[] parts = parseDeadlineInput();
        // Validate the input
        validate(parts);

        // Create the Deadline task
        Task task = new Deadline(parts[0].trim(), parts[1].trim());

        // Add task to the list and save
        tasks.add(task);
        storage.save(tasks.getTasks());

        // Show feedback in console
        ui.showAddedTask(task, tasks.size());

        // Prepare GUI feedback
        feedback = String.format(
                "Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                task,
                tasks.size()
        );
    }

    /**
     * Parses the user input for the deadline command into description and /by date.
     *
     * @return Array of 2 strings: [description, by], or empty array if invalid
     */
    private String[] parseDeadlineInput() {
        final String commandPrefix = "deadline "; // 9 characters
        String[] segments;

        if (input.length() <= commandPrefix.length()) {
            segments = new String[0];
        } else {
            segments = input.substring(commandPrefix.length()).split(" /by ", 2);
        }

        return segments;
    }

    /**
     * Validates the parsed input segments.
     *
     * @param parts Array of strings from parseDeadlineInput()
     * @throws SnowmanException if any segment is missing or empty
     */
    private void validate(String[] parts) throws SnowmanException {
        if (parts.length < 2
                || parts[0].trim().isEmpty()
                || parts[1].trim().isEmpty()) {
            throw new SnowmanException("The description or /by of a deadline cannot be empty.");
        }
    }
}
