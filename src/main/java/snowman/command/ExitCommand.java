package snowman.command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import snowman.storage.Storage;
import snowman.task.TaskList;
import snowman.ui.Ui;


/**
 * Represents a command that exits the Snowman application.
 * Displays a farewell message and signals application termination.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");

        feedback = "Bye. Hope to see you again soon!";

        // Wait 1 second before closing
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * {@inheritDoc}
     *
     * @return true to indicate that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
