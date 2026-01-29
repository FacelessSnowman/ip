package ip.src.main.java.snowman.command;

import ip.src.main.java.snowman.storage.Storage;
import ip.src.main.java.snowman.task.TaskList;
import ip.src.main.java.snowman.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.listTasks());
    }
}