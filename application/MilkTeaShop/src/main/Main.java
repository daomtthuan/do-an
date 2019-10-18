package main;

import access.DataProvider;
import controller.employee.Login;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

/**
 * Main application.
 */
public final class Main extends javafx.application.Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull javafx.stage.Stage stage) {
        try {
            // Setup primary Stage show view Login with controller Login for customer
            Stage.getInstance().setPrimary(stage);
            FXMLLoader primaryView = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            primaryView.setController(new Login());
            Stage.getInstance().getPrimary().setScene(new Scene(primaryView.load()));

            // Platform exit and close database connection when primary Stage close
            Stage.getInstance().getPrimary().setOnCloseRequest(windowEvent -> {
                DataProvider.getInstance().close();
                Platform.exit();
            });

            // Show primary Stage
            Stage.getInstance().getPrimary().show();
        } catch (Exception e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }
}
