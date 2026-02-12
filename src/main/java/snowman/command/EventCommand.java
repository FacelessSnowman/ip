package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Event;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command to add an Event task in the Snowman application.
 * When executed, it creates an Event with a description, start date, and end date,
 * adds it to the task list, updates storage, and shows a confirmation message.
 */
public class EventCommand extends Command {

    /** Full user input string for this command. */
    private final String input;

    /**
     * Constructs an EventCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {

        // Parse the input into description, from, and to
        String[] parts = parseEventInput();
        // Validate the parts
        validate(parts);

        // Create the Event task
        Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());

        // Add task to list and save
        tasks.add(task);
        storage.save(tasks.getTasks());

        // Create feedback message
        String message = String.format(
                "Got it. I've added this event:%n  %s%nNow you have %d tasks in the list.",
                task,
                tasks.size()
        );

        // Show in console
        ui.showMessage(message);
        // Show in GUI
        feedback = message;
    }

    /**
     * Parses the user input for the event command into description, from, and to segments.
     *
     * @return Array of 3 strings: [description, from, to], or empty array if invalid
     */
    private String[] parseEventInput() {
        final String commandPrefix = "event "; // 6 characters
        if (input.length() <= commandPrefix.length()) {
            return new String[0];
        }
        // Split by /from and /to
        return input.substring(commandPrefix.length()).split(" /from | /to ", 3);
    }

    /**
     * Validates the parsed input segments.
     *
     * @param parts Array of strings from parseEventInput()
     * @throws SnowmanException if any segment is missing or empty
     */
    private void validate(String[] parts) throws SnowmanException {
        if (parts.length < 3
                || parts[0].trim().isEmpty()
                || parts[1].trim().isEmpty()
                || parts[2].trim().isEmpty()) {
            throw new SnowmanException("The description, /from, /to of an event cannot be empty.");
        }
    }
}
