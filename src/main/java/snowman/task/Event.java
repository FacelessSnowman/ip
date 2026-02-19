package snowman.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import snowman.SnowmanException;

/**
 * Represents an Event task in the Snowman application.
 * An Event has a description, a start date, and an end date.
 * It can be marked as done or not done like other tasks.
 */
public class Event extends Task {
    /** Start date of the event. */
    protected LocalDate start;

    /** End date of the event. */
    protected LocalDate end;

    /**
     * Creates an Event task with the specified description, start date, and end date.
     * The dates must be in the format yyyy-MM-dd, otherwise a {@link SnowmanException} is thrown.
     * The task is initially marked as not done.
     *
     * @param description Description of the Event task.
     * @param start Start date of the event in yyyy-MM-dd format.
     * @param end End date of the event in yyyy-MM-dd format.
     * @throws SnowmanException If either start or end date format is invalid.
     */
    public Event(String description, String start, String end) throws SnowmanException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (Exception e) {
            throw new SnowmanException("Please use the date format yyyy-MM-dd");
        }
    }

    /**
     * Returns a string representation of the Event task for console display.
     * Includes task type, completion status, description, and formatted start and end dates.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Event task for saving to file.
     * Includes task type, completion status, description, start date, and end date in ISO format.
     *
     * @return String formatted for storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
