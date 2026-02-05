package snowman;

import snowman.command.Command;
import snowman.command.Parser;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;

/**
 * Represents the Snowman chatbot application.
 * Initializes the user interface, storage, and task list,
 * and processes user commands in a loop until the program exits.
 */
public class Snowman {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Snowman application instance.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath File path where tasks will be stored and loaded from.
     */
    public Snowman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Snowman application.
     * Sends a welcome message, then repeatedly reads commands from the user,
     * parses them, executes them, and handles errors until the user exits.
     */
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

    /**
     * Starts the Snowman application.
     * Creates a Snowman instance with a default storage file and runs it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Snowman("data/storage.txt").run();
    }
}
