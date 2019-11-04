package component.controller;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.TableFood;
import org.jetbrains.annotations.NotNull;
import tool.Number;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class TableFoodPane implements Controller, Initializable {
	@FXML
	private Pane tableFoodPane;

	@NotNull
	private Button createButton(@NotNull TableFood tableFood) {
		ImageView imageView = new ImageView("/asset/tablefood/" + Number.random(13) +".png");
		Button button = new Button("Table " + tableFood.getId(), imageView);
		button.getStyleClass().addAll("customButton", "tableFoodButton");
		button.setOnAction(actionEvent -> selectTableFood());
		button.setLayoutX(tableFood.getX());
		button.setLayoutY(tableFood.getY());
		return button;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<TableFood> tableFoods = api.TableFood.getInstance().getEnabledList();
		tableFoods.forEach(tableFood -> tableFoodPane.getChildren().add(createButton(tableFood)));
	}

	public abstract void selectTableFood();
}
