import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader viewLogin = new FXMLLoader(this.getClass().getResource("view/shared/Login.fxml"));
        viewLogin.setController(new controller.employee.Login());
        Parent parent = viewLogin.load();
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Milktea Shop");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
