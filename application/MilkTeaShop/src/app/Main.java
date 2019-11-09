package app;

import api.DataProvider;
import app.alert.AlertError;
import app.primary.PrimaryStage;
import controller.general.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
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
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/asset/brand/Logo.png")));

			PrimaryStage.getInstance().setStage(stage);
			PrimaryStage.getInstance().setScene("/view/general/Login.fxml", new Login());

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
