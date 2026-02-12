package snowman.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import snowman.SnowmanException;

/**
 * Represents a Deadline task in the Snowman application.
 * A Deadline has a description and a due date.
 * It can be marked as done or not done like other tasks.
 */
public class Deadline extends Task {
    protected LocalDate time;

    /**
     * Constructs a Deadline task with the specified description and due date.
     * The date must be in the format yyyy-MM-dd, otherwise a {@link SnowmanException} is thrown.
     * The task is initially marked as not done.
     *
     * @param description Description of the Deadline task.
     * @param time Due date of the task in yyyy-MM-dd format.
     * @throws SnowmanException If the date format is invalid.
     */
    public Deadline(String description, String time) throws SnowmanException {
        super(description);
        try {
            this.time = LocalDate.parse(time); // expects yyyy-MM-dd
        } catch (Exception e) {
            throw new SnowmanException("Please use the date format yyyy-MM-dd");
        }
    }

    /**
     * Returns the due date of the deadline task.
     *
     * @return Due date as a {@link LocalDate}.
     */
    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + time;
    }
}
