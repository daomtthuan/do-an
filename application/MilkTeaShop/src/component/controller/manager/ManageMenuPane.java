package component.controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import component.controller.general.MenuPane;
import component.controller.manager.managepane.ManageCategoryPane;
import component.controller.manager.managepane.ManageFoodPane;
import controller.manager.EditCategory;
import controller.manager.EditFood;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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

				AtomicBoolean categoryShow = new AtomicBoolean(false);
				AtomicBoolean foodShow = new AtomicBoolean(false);

				MenuItem insertCategoryMenuItem = new MenuItem("Insert");
				insertCategoryMenuItem.setOnAction(actionEvent -> {
					refresh = () -> manageMenu();
					PrimaryDialog.getInstance().setScene("/view/manager/EditCategory.fxml", new EditCategory(this));
					PrimaryDialog.getInstance().getStage().show();
					PrimaryStage.getInstance().getStage().hide();
				});

				MenuItem insertFoodMenuItem = new MenuItem("Insert");
				insertFoodMenuItem.setOnAction(actionEvent -> {
					PrimaryDialog.getInstance().setScene("/view/manager/EditFood.fxml", new EditFood(this, categories));
					PrimaryDialog.getInstance().getStage().show();
					PrimaryStage.getInstance().getStage().hide();
				});

				ContextMenu categoryContextMenu = new ContextMenu(insertCategoryMenuItem);
				ContextMenu foodContextMenu = new ContextMenu(insertFoodMenuItem);

				getCategoryScrollPane().setOnContextMenuRequested(contextMenuEvent -> {
					if (!categoryShow.get()) {
						refresh = () -> manageMenu();
						categoryContextMenu.show(getCategoryScrollPane(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
					}
				});
				getCategoryScrollPane().setOnMouseClicked(mouseEvent -> {
					if (categoryContextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
						categoryContextMenu.hide();
					}
				});

				categories.forEach(category -> {
					Button categoryButton = createButton(category.getName(), "/asset/category/" + category.getId() + ".png", "categoryButton");

					categoryButton.setOnAction(categoryActionEvent -> {
						getFoodPane().getChildren().clear();
						api.Food.getInstance().getEnabledFoods(category.getId()).forEach(food -> {
							Button foodButton = createButton(food.getName() + "\n$" + food.getPrice(), "/asset/food/" + food.getId() + ".png", "foodButton");

							MenuItem insertMenuItem = new MenuItem("Insert");
							insertMenuItem.setOnAction(actionEvent -> {
								refresh = categoryButton::fire;
								PrimaryDialog.getInstance().setScene("/view/manager/EditFood.fxml", new EditFood(this, categories, categories.indexOf(category), category));
								PrimaryDialog.getInstance().getStage().show();
								PrimaryStage.getInstance().getStage().hide();
							});

							MenuItem updateMenuItem = new MenuItem("Update");
							updateMenuItem.setOnAction(actionEvent -> {
								refresh = categoryButton::fire;
								PrimaryDialog.getInstance().setScene("/view/manager/EditFood.fxml", new EditFood(this, categories, categories.indexOf(category), category, food));
								PrimaryDialog.getInstance().getStage().show();
								PrimaryStage.getInstance().getStage().hide();
							});

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
										AlertWarning.getInstance().showAndWait("Fail!", "Cannot change image.\nPlease notify staff.");
									}
								}
							});

							MenuItem deleteMenuItem = new MenuItem("Delete");
							deleteMenuItem.setOnAction(actionEvent -> {
								if (api.Food.getInstance().delete(food.getId()) != null) {
									AlertWarning.getInstance().showAndWait("Fail!", "Cannot delete category.\nPlease notify staff.");
								} else {
									categoryButton.fire();
								}
							});

							ContextMenu contextMenu = new ContextMenu(insertMenuItem, updateMenuItem, changeImageFood, deleteMenuItem);
							contextMenu.setOnHidden(windowEvent -> foodShow.set(false));
							foodButton.setOnContextMenuRequested(contextMenuEvent -> {
								foodShow.set(true);
								foodContextMenu.hide();
								contextMenu.show(foodButton, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
							});
							foodButton.setOnMouseClicked(mouseEvent -> {
								foodContextMenu.hide();
								if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
									contextMenu.hide();
								}
							});
							getFoodPane().getChildren().add(foodButton);
						});
					});

					getFoodScrollPane().setOnContextMenuRequested(contextMenuEvent -> {
						if (!foodShow.get()) {
							refresh = categoryButton::fire;
							foodContextMenu.show(getFoodScrollPane(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
						}
					});
					getFoodScrollPane().setOnMouseClicked(mouseEvent -> {
						if (foodContextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
							foodContextMenu.hide();
						}
					});

					MenuItem insertMenuItem = new MenuItem("Insert");
					insertMenuItem.setOnAction(actionEvent -> {
						refresh = () -> manageMenu();
						PrimaryDialog.getInstance().setScene("/view/manager/EditCategory.fxml", new EditCategory(this));
						PrimaryDialog.getInstance().getStage().show();
						PrimaryStage.getInstance().getStage().hide();
					});

					MenuItem updateMenuItem = new MenuItem("Update");
					updateMenuItem.setOnAction(actionEvent -> {
						refresh = () -> manageMenu();
						PrimaryDialog.getInstance().setScene("/view/manager/EditCategory.fxml", new EditCategory(this, category));
						PrimaryDialog.getInstance().getStage().show();
						PrimaryStage.getInstance().getStage().hide();
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
								AlertWarning.getInstance().showAndWait("Fail!", "Cannot change image.\nPlease notify staff.");
							}
						}
					});

					MenuItem deleteMenuItem = new MenuItem("Delete");
					deleteMenuItem.setOnAction(actionEvent -> {
						if (api.Category.getInstance().delete(category.getId()) != null) {
							AlertWarning.getInstance().showAndWait("Fail!", "Cannot delete category.\nBecause some foods are belonged this category.");
						} else {
							manageMenu();
						}
					});

					ContextMenu contextMenu = new ContextMenu(insertMenuItem, updateMenuItem, changeImageCategory, deleteMenuItem);
					contextMenu.setOnHidden(windowEvent -> categoryShow.set(false));
					categoryButton.setOnContextMenuRequested(contextMenuEvent -> {
						categoryShow.set(true);
						categoryContextMenu.hide();
						contextMenu.show(categoryButton, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
					});
					categoryButton.setOnMouseClicked(mouseEvent -> {
						categoryContextMenu.hide();
						if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
							contextMenu.hide();
						}
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
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/managepane/ManagePane.fxml", new ManageCategoryPane()));
	}

	@FXML
	private void manageFood() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/managepane/ManageFoodPane.fxml", new ManageFoodPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageMenu();
	}
}
