package ip.src.main.java.snowman;

import ip.src.main.java.snowman.command.Command;
import ip.src.main.java.snowman.command.Parser;
import ip.src.main.java.snowman.storage.Storage;
import ip.src.main.java.snowman.task.TaskList;
import ip.src.main.java.snowman.ui.Ui;

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