package snowman.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Event;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents a command that adds an Event task to the Snowman application.
 * Executes the addition of an Event task with a description, start time,
 * and end time, updates storage, and prepares user feedback.
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

    /**
     * Executes the event command by parsing the input, creating an Event task,
     * adding it to the task list, saving the updated list to storage,
     * and preparing feedback.
     *
     * @param tasks Task list to which the new task is added.
     * @param ui User interface used to display feedback.
     * @param storage Storage used to persist tasks.
     * @throws SnowmanException If the input format is invalid.
     */
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
     * Validates the parsed input segments to ensure all required fields are present.
     *
     * @param parts Parsed input segments.
     * @throws SnowmanException If the description, start time, or end time is missing or empty.
     */
    private void validate(String[] parts) throws SnowmanException {
        if (parts.length < 3
                || parts[0].trim().isEmpty()
                || parts[1].trim().isEmpty()
                || parts[2].trim().isEmpty()) {
            throw new SnowmanException("The description, /from, /to of an event cannot be empty.");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate from = LocalDate.parse(parts[1].trim(), formatter);
            LocalDate to = LocalDate.parse(parts[2].trim(), formatter);

            if (from.isAfter(to)) {
                throw new SnowmanException("The /from date cannot be later than the /to date.");
            }

        } catch (DateTimeParseException e) {
            throw new SnowmanException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
