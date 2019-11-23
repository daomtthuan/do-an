package app;

import api.DataProvider;
import app.alert.AlertError;
import app.primary.PrimaryStage;
import controller.general.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Path billPath = Paths.get("./bill");
		Path assetPath = Paths.get("./asset/category");
		Path categoryPath = Paths.get("./asset/category");
		Path foodPath = Paths.get("./asset/food");
		try {
			if (!Files.exists(billPath)) {
				Files.createDirectories(billPath);
			}
			if (!Files.exists(assetPath)) {
				Files.createDirectories(assetPath);
			}
			if (!Files.exists(categoryPath)) {
				Files.createDirectories(categoryPath);
			}
			if (!Files.exists(foodPath)) {
				Files.createDirectories(foodPath);
			}
		} catch (IOException e) {
			AlertError.getInstance().showAndWait(e);
			Platform.exit();
		}

		try {
			stage.setTitle("Milk Tea Shop");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/asset/brand/Logo.png")));

			PrimaryStage.getInstance().setStage(stage);
			PrimaryStage.getInstance().setScene("/view/general/Login.fxml", new Login("Admin"));

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
