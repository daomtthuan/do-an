package component.controller;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Table;
import org.jetbrains.annotations.NotNull;
import tool.Number;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class TablePane implements Controller, Initializable {
	@FXML
	private Pane tablePane;

	@NotNull
	private Button createButton(@NotNull Table table) {
		ImageView imageView = new ImageView("/asset/table/" + Number.random(13) + ".png");
		Button button = new Button("Table " + table.getId(), imageView);
		button.getStyleClass().addAll("customButton", "tableButton");
		button.setOnAction(actionEvent -> selectTable(table));
		button.setLayoutX(table.getX());
		button.setLayoutY(table.getY());
		return button;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<Table> tables = api.Table.getInstance().getEnabledList();
		tables.forEach(table -> tablePane.getChildren().add(createButton(table)));
	}

	public abstract void selectTable(Table table);
}
