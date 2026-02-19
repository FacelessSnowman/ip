package snowman;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Snowman application.
 * Handles user input, displays dialog boxes for both the user and Snowman,
 * and maintains the scrollable dialog container in the GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Snowman snowman;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_fireboy.png"));
    private Image snowmanImage = new Image(this.getClass().getResourceAsStream("/images/snowman.png"));

    /**
     * Initializes the GUI components.
     * <p>
     * Binds the vertical scroll value of the ScrollPane to the height of
     * the dialog container so that it scrolls automatically as new dialog boxes are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Snowman instance into the GUI controller.
     *
     * @param d Snowman application instance.
     */
    public void setSnowman(Snowman d) {
        snowman = d;
    }

    /**
     * Handles user input from the text field.
     * <p>
     * Creates two dialog boxes: one showing the user's input and
     * the other showing Snowman's response. Appends the dialog boxes
     * to the dialog container and clears the user input field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = snowman.getResponse(input);
        String commandType = snowman.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSnowmanDialog(response, snowmanImage, commandType)
        );
        userInput.clear();
    }
}
