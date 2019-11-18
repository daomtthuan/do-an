package component.controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.general.TablePane;
import component.controller.manager.managepane.ManageBillPane;
import component.controller.manager.managepane.ManageDiscountPane;
import component.controller.manager.managepane.ManageTablePane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import model.Table;
import tool.Delta;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class ManageShopPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@FXML
	private void managePositionTable() {
		subManageComponent.getChildren().clear();
		AtomicBoolean show = new AtomicBoolean(false);
		MenuItem insertMenuItem = new MenuItem("Insert");
		insertMenuItem.setOnAction(actionEvent -> {
			if (api.Table.getInstance().insert(10, 10) != null) {
				managePositionTable();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Cannot insert table.\nPlease notify staff.");
			}
		});
		ContextMenu contextMenuTablePane = new ContextMenu(insertMenuItem);

		TablePane tablePane = new TablePane() {
			@Override
			protected void setup() {
				ArrayList<Table> tables = api.Table.getInstance().getEnabledTables();
				tables.forEach(table -> {
					Button tableButton = createButton(table);
					Delta oldDelta = new Delta(tableButton.getLayoutX(), tableButton.getLayoutY());
					Delta newDelta = new Delta();
					tableButton.setOnMousePressed(mouseEvent -> {
						if (mouseEvent.getButton() == MouseButton.PRIMARY) {
							newDelta.setX(tableButton.getLayoutX() - mouseEvent.getSceneX());
							newDelta.setY(tableButton.getLayoutY() - mouseEvent.getSceneY());
						}
					});
					tableButton.setOnMouseDragged(mouseEvent -> {
						if (mouseEvent.getButton() == MouseButton.PRIMARY) {
							tableButton.setLayoutX(mouseEvent.getSceneX() + newDelta.getX());
							tableButton.setLayoutY(mouseEvent.getSceneY() + newDelta.getY());
						}
					});
					tableButton.setOnMouseReleased(mouseEvent -> {
						if ((oldDelta.getX() != newDelta.getX() || oldDelta.getY() != newDelta.getY()) && mouseEvent.getButton() == MouseButton.PRIMARY) {
							if (api.Table.getInstance().update(table.getId(), tableButton.getLayoutX(), tableButton.getLayoutY()) == null) {
								AlertWarning.getInstance().showAndWait("Fail!", "Cannot update table.\nPlease notify staff.");
							}
						}
					});
					tableButton.setDisable(false);

					MenuItem insertMenuItem = new MenuItem("Insert");
					insertMenuItem.setOnAction(actionEvent -> {
						if (api.Table.getInstance().insert(10, 10) != null) {
							managePositionTable();
						} else {
							AlertWarning.getInstance().showAndWait("Fail!", "Cannot insert table.\nPlease notify staff.");
						}
					});

					MenuItem deleteMenuItem = new MenuItem("Delete");
					deleteMenuItem.setOnAction(actionEvent -> {
						if (api.Table.getInstance().delete(table.getId()) != null) {
							AlertWarning.getInstance().showAndWait("Fail!", "Cannot delete table.\nBecause some bills are using information of this table.");
						} else {
							managePositionTable();
						}
					});

					ContextMenu contextMenu = new ContextMenu(insertMenuItem, deleteMenuItem);
					contextMenu.setOnHidden(windowEvent -> show.set(false));
					tableButton.setOnContextMenuRequested(contextMenuEvent -> {
						show.set(true);
						contextMenuTablePane.hide();
						contextMenu.show(tableButton, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
					});
					tableButton.setOnMouseClicked(mouseEvent -> {
						contextMenuTablePane.hide();
						if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
							contextMenu.hide();
						}
					});

					getTablePane().getChildren().add(tableButton);
				});
			}
		};
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/TablePane.fxml", tablePane));

		tablePane.getTablePane().setOnContextMenuRequested(contextMenuEvent -> {
			if (!show.get()) {
				contextMenuTablePane.show(tablePane.getTablePane(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
			}
		});
		tablePane.getTablePane().setOnMouseClicked(mouseEvent -> {
			if (contextMenuTablePane.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
				contextMenuTablePane.hide();
			}
		});
	}

	@FXML
	private void manageTable() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/managepane/ManagePane.fxml", new ManageTablePane()));
	}

	@FXML
	private void manageBill() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/managepane/ManageBillPane.fxml", new ManageBillPane()));
	}

	@FXML
	private void manageDiscount() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/managepane/ManagePane.fxml", new ManageDiscountPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		managePositionTable();
	}
}
