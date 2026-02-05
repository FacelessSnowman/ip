package snowman.command;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.task.Task;
import snowman.task.TaskList;
import snowman.task.Todo;
import snowman.ui.Ui;

/**
 * Represents a command to add a Todo task in the Snowman application.
 * When executed, it creates a Todo with the given description,
 * adds it to the task list, updates the storage, and shows a confirmation message.
 */
public class TodoCommand extends Command {
    private final String input;

    /**
     * Constructs a TodoCommand with the specified user input.
     *
     * @param input Full command input from the user.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String inputSegment = input.length() > 4 ? input.substring(5).trim() : "";
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
    }
}
