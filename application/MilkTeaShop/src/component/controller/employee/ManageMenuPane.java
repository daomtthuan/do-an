package component.controller.employee;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.general.MenuPane;
import component.controller.general.managepane.ManageCategoryPane;
import component.controller.general.managepane.ManageFoodPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Category;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageMenuPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@FXML
	private void manageMenu() {
		subManageComponent.getChildren().clear();
		MenuPane menuPane = new MenuPane() {
			@Override
			protected void setup() {
				ArrayList<Category> categories = api.Category.getInstance().getEnabledCategories();
				categories.forEach(category -> {
					Button categoryButton = createButton(category.getName(), "asset/category/" + category.getId() + ".png", "categoryButton");

					categoryButton.setOnAction(categoryActionEvent -> {
						getFoodPane().getChildren().clear();
						api.Food.getInstance().getEnabledFoods(category.getId()).forEach(food -> {
							Button foodButton = createButton(food.getName() + "\n$" + food.getPrice(), "asset/food/" + food.getId() + ".png", "foodButton");
							getFoodPane().getChildren().add(foodButton);
						});
					});
					getCategoryPane().getChildren().add(categoryButton);
				});
			}
		};
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/MenuPane.fxml", menuPane));
	}

	@FXML
	private void manageCategory() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageCategoryPane()));
	}

	@FXML
	private void manageFood() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManageFoodPane.fxml", new ManageFoodPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageMenu();
	}
}
