package component.controller;

import app.pattern.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Table;
import tool.Number;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class TablePane implements Controller, Initializable {
	@FXML
	private Pane tablePane;

	protected Button createButton(Table table) {
		ImageView imageView = new ImageView("/asset/table/" + Number.random(12) + ".png");
		Button button = new Button(String.valueOf(table.getId()), imageView);
		button.getStyleClass().addAll("customButton", "tableButton");
		button.setLayoutX(table.getX());
		button.setLayoutY(table.getY());
		button.setDisable(table.isBusy());
		return button;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		setup();
	}

	protected Pane getTablePane() {
		return tablePane;
	}

	protected abstract void setup();
}
