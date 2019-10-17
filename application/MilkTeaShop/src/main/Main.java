package main;

import access.DataProvider;
import controller.employee.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

public class Main extends Application {
    private static Stage primaryStage;
    private static Stage secondaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static void setSecondaryStage(Stage secondaryStage) {
        Main.secondaryStage = secondaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage stage) {
        setPrimaryStage(stage);
        try {
            // Load view and set controller
            FXMLLoader primaryView = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            primaryView.setController(new Login());

            // Set scene for primaryStage
            primaryStage.setTitle("Milk Tea Shop");
            primaryStage.setScene(new Scene(primaryView.load()));
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
