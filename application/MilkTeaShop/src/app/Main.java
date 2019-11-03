package app;

import api.DataProvider;
import app.alert.AlertError;
import app.stage.PrimaryStage;
import controller.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public final class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage stage) {
        try {
            stage.setTitle("Milk Tea Shop");

            PrimaryStage.getInstance().setStage(stage);
            PrimaryStage.getInstance().setScene("/view/Login.fxml", new Login());

            PrimaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                DataProvider.getInstance().closeConnection();
                Platform.exit();
            });

            PrimaryStage.getInstance().getStage().show();
        } catch (Exception e) {
            AlertError.getInstance().showAndWait(e);
        }
    }
}
