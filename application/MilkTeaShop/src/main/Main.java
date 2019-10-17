package main;

import access.DataProvider;
import controller.employee.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ErrorAlert;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        try {
            // Load view and set controller
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            view.setController(new Login());

            // Set scene for primaryStage
            primaryStage.setTitle("Milk Tea Shop");
            primaryStage.setScene(new Scene(view.load()));
            primaryStage.setResizable(false);

            // Set event OnCloseRequest for primaryStage
            primaryStage.setOnCloseRequest(event -> {
                try {
                    DataProvider.getInstance().close();
                } catch (Exception e) {
                    ErrorAlert.getInstance().showAndWait(e);
                }
                Platform.exit();
            });

            primaryStage.show();
        } catch (Exception e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

}
