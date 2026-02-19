package snowman;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Snowman GUI application using JavaFX.
 * <p>
 * Initializes the primary stage, loads the FXML layout, injects the Snowman instance
 * into the GUI controller, and displays the main window. Handles stage sizing
 * and ensures the GUI is correctly linked to the underlying Snowman application logic.
 */
public class Main extends Application {
    private final Snowman snowman = new Snowman("data/storage.txt");

    @Override
    /**
     * Starts the JavaFX application by setting up the primary stage.
     * <p>
     * Loads the FXML layout, creates a scene, sets stage size limits, injects
     * the Snowman instance into the controller, and displays the window.
     *
     * @param stage The primary stage for this application.
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);
            fxmlLoader.<MainWindow>getController().setSnowman(snowman); // inject the Snowman instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
