package ip.src.main.java.snowman.command;

import ip.src.main.java.snowman.SnowmanException;
import ip.src.main.java.snowman.storage.Storage;
import ip.src.main.java.snowman.task.TaskList;
import ip.src.main.java.snowman.task.Event;
import ip.src.main.java.snowman.task.Task;
import ip.src.main.java.snowman.ui.Ui;

public class EventCommand extends Command {
    private final String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnowmanException {
        String[] inputSegment = input.length() > 5 ? input.substring(6).split(" /from | /to ", 3) : new String[0];
        if (inputSegment.length < 3
                || inputSegment[0].trim().isEmpty()
                || inputSegment[1].trim().isEmpty()
                || inputSegment[2].trim().isEmpty()) {
            throw new SnowmanException("The description, /from, /to of an event cannot be empty.");
        }
        Task task = new Event(inputSegment[0].trim(), inputSegment[1].trim(), inputSegment[2].trim());
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
