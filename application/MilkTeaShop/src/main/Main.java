package main;

import access.DataProvider;
import controller.ControllerAdminLogin;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

/**
 * The type Main.
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

            // Setup primary Stage show view AdminLogin with controller AdminLogin for customer
            PrimaryStage.getInstance().setStage(stage);
            PrimaryStage.getInstance().setScene("/view/ViewLogin.fxml", new ControllerAdminLogin());

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
