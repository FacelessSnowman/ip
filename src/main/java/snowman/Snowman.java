package snowman;

import snowman.command.Command;
import snowman.command.Parser;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

public class Snowman {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Snowman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SnowmanException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Snowman("data/storage.txt").run();
    }
}