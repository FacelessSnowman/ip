package snowman;

import javafx.application.Application;

/**
 * Launches the Snowman application.
 * Acts as a workaround for JavaFX classpath issues when starting the application.
 */
public class Launcher {
    /**
     * Entry point of the application.
     * Launches the JavaFX Main class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
