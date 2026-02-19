package snowman.task;

/**
 * Represents a Todo task in the Snowman application.
 * <p>
 * A Todo is a simple task that contains only a description and a completion status.
 * It does not have any associated date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for display purposes.
     *
     * @return String in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task suitable for saving to a file.
     *
     * @return String in the format "T | status | description".
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
