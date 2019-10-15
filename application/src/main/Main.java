package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        // Load view and set controller
        FXMLLoader viewEmployeeLogin = new FXMLLoader(getClass().getResource("/view/global/Login.fxml"));
        viewEmployeeLogin.setController(new controller.employee.Login());

        // Set scene for primaryStage
        primaryStage.setTitle("Milk Tea Shop");
        primaryStage.setScene(new Scene(viewEmployeeLogin.load()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
