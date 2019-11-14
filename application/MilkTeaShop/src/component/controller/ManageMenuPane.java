package component.controller;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.managepane.ManageCategoryPane;
import component.controller.managepane.ManageFoodPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.Category;
import model.Food;

import java.io.File;
import java.io.IOException;
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
				FileChooser fileChooser = new FileChooser();
				categories.forEach(category -> {
					Button categoryButton = createButton(category.getName(), "/asset/category/" + category.getId() + ".png", "categoryButton");
					categoryButton.setOnAction(categoryActionEvent -> {
						getFoodPane().getChildren().clear();
						ArrayList<Food> foods = api.Food.getInstance().getEnabledFoods(category.getId());
						foods.forEach(food -> {
							Button foodButton = createButton(food.getName() + "\n$" + food.getPrice(), "/asset/food/" + food.getId() + ".png", "foodButton");
							MenuItem changeImageFood = new MenuItem("Change image");
							changeImageFood.setOnAction(actionEvent -> {
								File file = fileChooser.showOpenDialog(PrimaryStage.getInstance().getStage());
								if (file != null) {
									try {
										File image = new File("./src/asset/food/" + food.getId() + ".png").getCanonicalFile();
										tool.File.copy(file.getAbsoluteFile(), image);
										tool.File.copy(file.getAbsoluteFile(), new File("./out/production/MilkTeaShop/asset/food/" + food.getId() + ".png").getCanonicalFile());
										ImageView imageView = new ImageView(image.toURI().toURL().toExternalForm());
										imageView.setFitHeight(120);
										imageView.setFitWidth(120);
										foodButton.setGraphic(imageView);
									} catch (IOException e) {
										AlertWarning.getInstance().showAndWait("Fail!", "Can not change image.\nPlease notify staff.");
									}
								}
							});
							ContextMenu contextMenu = new ContextMenu(changeImageFood);
							foodButton.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(foodButton, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
							foodButton.setOnMouseClicked(mouseEvent -> {
								if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
									contextMenu.hide();
								}
							});
							getFoodPane().getChildren().add(foodButton);
						});
					});

					MenuItem changeImageCategory = new MenuItem("Change image");
					changeImageCategory.setOnAction(actionEvent -> {
						File file = fileChooser.showOpenDialog(PrimaryStage.getInstance().getStage());
						if (file != null) {
							try {
								File image = new File("./src/asset/category/" + category.getId() + ".png").getCanonicalFile();
								tool.File.copy(file.getAbsoluteFile(), image);
								tool.File.copy(file.getAbsoluteFile(), new File("./out/production/MilkTeaShop/asset/category/" + category.getId() + ".png").getCanonicalFile());
								ImageView imageView = new ImageView(image.toURI().toURL().toExternalForm());
								imageView.setFitHeight(120);
								imageView.setFitWidth(120);
								categoryButton.setGraphic(imageView);
							} catch (IOException e) {
								AlertWarning.getInstance().showAndWait("Fail!", "Can not change image.\nPlease notify staff.");
							}
						}
					});
					ContextMenu contextMenu = new ContextMenu(changeImageCategory);
					categoryButton.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(categoryButton, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
					categoryButton.setOnMouseClicked(mouseEvent -> {
						if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
							contextMenu.hide();
						}
					});
					getCategoryPane().getChildren().add(categoryButton);
				});
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
