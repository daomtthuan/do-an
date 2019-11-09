package app.primary;

import app.pattern.Stage;
import javafx.scene.image.Image;

public class PrimarySubDialog extends Stage {
	private static PrimarySubDialog instance;

	private PrimarySubDialog() {
		setStage(new javafx.stage.Stage());
		getStage().setTitle("Milk Tea Shop - Dialog");
		getStage().setOnCloseRequest(windowEvent -> {
			windowEvent.consume();
			getStage().hide();
		});
		getStage().getIcons().add(new Image(getClass().getResourceAsStream("/asset/brand/Logo.png")));
	}

	public static PrimarySubDialog getInstance() {
		if (instance == null) {
			setInstance(new PrimarySubDialog());
		}
		return instance;
	}

	private static void setInstance(PrimarySubDialog instance) {
		PrimarySubDialog.instance = instance;
	}
}
