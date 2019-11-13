package component.controller;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.managepane.ManageCategoryPane;
import component.controller.managepane.ManageFoodPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Category;
import model.Food;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageMenuPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@FXML
	private void manageMenu() {
		subManageComponent.getChildren().clear();
		MenuPane menuPane = new MenuPane() {
			@Override
			public void selectFood(Category category, Food food) {
			}
		};
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/MenuPane.fxml", menuPane));
	}

	@FXML
	private void manageCategory() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/managepane/ManagePane.fxml", new ManageCategoryPane()));
	}

	@FXML
	private void manageFood() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/managepane/ManageFoodPane.fxml", new ManageFoodPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageMenu();
	}
}
