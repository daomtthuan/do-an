package main;

import access.DataProvider;
import controller.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

/**
 * Main application.
 */
public final class Main extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage stage) {
        try {
            stage.setTitle("Milk Tea Shop");
            stage.setResizable(false);

            // Setup primary Stage show view Login with controller Login for customer
            PrimaryStage.getInstance().setStage(stage);
            FXMLLoader primaryView = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            primaryView.setController(new Login());
            PrimaryStage.getInstance().getStage().setScene(new Scene(primaryView.load()));

            // Platform exit and close database connection when primary Stage close
            PrimaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                DataProvider.getInstance().close();
                Platform.exit();
            });

            // Show primary Stage
            PrimaryStage.getInstance().getStage().show();
        } catch (Exception e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }
}
