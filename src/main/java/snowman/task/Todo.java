package snowman.task;

/**
 * Represents a Todo task in the Snowman application.
 * A Todo is a simple task that has only a description and a completion status.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
