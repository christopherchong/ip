import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import haru.Haru;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Haru using FXML.
 */
public class Main extends Application {
    private final Path filePath = Paths.get("src", "data", "haru.txt");
    private final Haru haru = new Haru(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHaru(haru);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
