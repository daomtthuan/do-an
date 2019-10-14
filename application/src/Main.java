import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent main = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
            Scene scene = new Scene(main);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
