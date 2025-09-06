import haru.Haru;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Haru haru;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image haruImage = new Image(this.getClass().getResourceAsStream("/images/haru.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Haru instance */
    public void setHaru(Haru h) {
        haru = h;
        // Show greeting once when Haru is set
        dialogContainer.getChildren().add(
                DialogBox.getHaruDialog(haru.showGreetings(), haruImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = haru.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHaruDialog(response, haruImage)
        );
        userInput.clear();
    }
}
