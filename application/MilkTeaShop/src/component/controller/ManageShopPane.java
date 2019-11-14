package component.controller;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Table;
import tool.Delta;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageShopPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@FXML
	private void manageTable() {
		subManageComponent.getChildren().clear();
		TablePane tablePane = new TablePane() {
			@Override
			protected void setup() {
				ArrayList<Table> tables = api.Table.getInstance().getEnabledTables();
				tables.forEach(table -> {
					Button tableButton = createButton(table);
					Delta oldDelta = new Delta(tableButton.getLayoutX(),tableButton.getLayoutY());
					Delta newDelta = new Delta();
					tableButton.setOnMousePressed(mouseEvent -> {
						newDelta.setX(tableButton.getLayoutX() - mouseEvent.getSceneX());
						newDelta.setY(tableButton.getLayoutY() - mouseEvent.getSceneY());
					});
					tableButton.setOnMouseDragged(mouseEvent -> {
						tableButton.setLayoutX(mouseEvent.getSceneX() + newDelta.getX());
						tableButton.setLayoutY(mouseEvent.getSceneY() + newDelta.getY());
					});
					tableButton.setOnMouseReleased(mouseEvent -> {
						if (oldDelta.getX() != newDelta.getX() || oldDelta.getY() != newDelta.getY()) {
							api.Table.getInstance().
						}
					});

					getTablePane().getChildren().add(tableButton);
				});
			}
		};
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/TablePane.fxml", tablePane));
	}

	@FXML
	private void manageBill() {

	}

	@FXML
	private void manageDiscount() {
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageTable();
	}
}
