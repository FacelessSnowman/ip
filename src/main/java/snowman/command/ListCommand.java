package snowman.command;

import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.listTasks());
    }
}