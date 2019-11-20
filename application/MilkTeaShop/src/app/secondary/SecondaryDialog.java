package app.secondary;

import app.alert.AlertError;
import app.pattern.Controller;
import app.pattern.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

public class SecondaryDialog extends Stage {
	private static SecondaryDialog instance;
	private double x;
	private double y;
	private double width;
	private double height;

	private SecondaryDialog() {
		setStage(new javafx.stage.Stage());
		getStage().setTitle("Milk Tea Shop - Customer - Dialog");
		getStage().setOnCloseRequest(windowEvent -> {
			windowEvent.consume();
			SecondaryStage.getInstance().getStage().show();
			getStage().hide();
		});
		getStage().getIcons().add(new Image(getClass().getResourceAsStream("/asset/brand/Logo.png")));
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	public static SecondaryDialog getInstance() {
		if (instance == null) {
			setInstance(new SecondaryDialog());
		}
		return instance;
	}

	private static void setInstance(SecondaryDialog instance) {
		SecondaryDialog.instance = instance;
	}

	public void close() {
		SecondaryStage.getInstance().getStage().show();
		getStage().hide();
	}

	@Override
	public void setScene(String view, Controller controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			loader.setController(controller);
			Scene scene = new Scene(loader.load());
			new JMetro(scene, Style.LIGHT);
			scene.getStylesheets().add("/style/general/Style.css");
			getStage().setScene(scene);
			setSize();
			SecondaryStage.getInstance().getStage().setOnShown(windowEvent -> setSize());
		} catch (IOException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	@Override
	public void setScene(String view, String style, Controller controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			loader.setController(controller);
			Scene scene = new Scene(loader.load());
			new JMetro(scene, Style.LIGHT);
			scene.getStylesheets().add("/style/general/Style.css");
			scene.getStylesheets().add(style);
			getStage().setScene(scene);
			setSize();
			SecondaryStage.getInstance().getStage().setOnShown(windowEvent -> setSize());
		} catch (IOException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	private void setSize() {
		if (x != 0) {
			getStage().setX(x);
			getStage().setY(y);
			getStage().setWidth(width);
			getStage().setHeight(height);
			getStage().setMaximized(true);
		}
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
