package snowman.task;

/**
 * Represents a generic task in the Snowman application.
 * Stores the task description and completion status.
 * Serves as a base class for specific types of tasks such as {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {

    /** Description of the task. */
    protected String description;

    /** Completion status of the task. */
    protected boolean isDone;

    /**
     * Creates a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the completion state of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * Specific task types must implement this method.
     *
     * @return String representing the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Returns a string representation of the task for console display.
     * Includes the status icon and the description.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
