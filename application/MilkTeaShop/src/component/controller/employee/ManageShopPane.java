package component.controller.employee;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryStage;
import app.primary.PrimarySubDialog;
import component.controller.general.TablePane;
import component.controller.general.managepane.ManageBillPane;
import component.controller.general.managepane.ManageDiscountPane;
import component.controller.general.managepane.ManageTablePane;
import controller.employee.ChangeTable;
import controller.employee.ManageBillDetail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import model.Table;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageShopPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	public ManageShopPane() {
		PrimaryStage.getInstance().setRefresh(this::managePositionTable);
	}

	@FXML
	private void managePositionTable() {
		subManageComponent.getChildren().clear();
		TablePane tablePane = new TablePane() {
			@Override
			protected void setup() {
				ArrayList<Table> tables = api.Table.getInstance().getEnabledTables();
				tables.forEach(table -> {
					Button tableButton = createButton(table);

					if (table.isBusy()) {
						MenuItem billDetailMenuItem = new MenuItem("Watch Bill details");
						billDetailMenuItem.setOnAction(actionEvent -> {
							PrimarySubDialog.getInstance().setScene("/view/employee/ManageBillDetail.fxml", "/style/employee/ManageBillDetail.css", new ManageBillDetail(table));
							PrimarySubDialog.getInstance().getStage().show();
							PrimaryStage.getInstance().getStage().hide();
						});

						MenuItem checkOutMenuItem = new MenuItem("Check out");
						checkOutMenuItem.setOnAction(actionEvent -> {
							if (api.Bill.getInstance().checkOut(table.getId()) != null) {
								managePositionTable();
							} else {
								AlertWarning.getInstance().showAndWait("Fail!", "Cannot check out table.\nPlease notify staff.");
							}
						});

						MenuItem changeTableMenuItem = new MenuItem("Change table");
						changeTableMenuItem.setOnAction(actionEvent -> {
							PrimarySubDialog.getInstance().setScene("/view/employee/ChangeTable.fxml", new ChangeTable(table, () -> managePositionTable()));
							PrimarySubDialog.getInstance().getStage().show();
							PrimaryStage.getInstance().getStage().hide();
						});

						ContextMenu contextMenu = new ContextMenu(billDetailMenuItem, checkOutMenuItem, changeTableMenuItem);
						tableButton.setOnContextMenuRequested(contextMenuEvent -> {
							contextMenu.show(tableButton, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
						});

						tableButton.setOnMouseClicked(mouseEvent -> {
							if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
								contextMenu.hide();
							}
						});
					}

					getTablePane().getChildren().add(tableButton);
				});
			}
		};
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/TablePane.fxml", tablePane));
	}

	@FXML
	private void manageTable() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageTablePane()));
	}

	@FXML
	private void manageBill() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManageBillPane.fxml", new ManageBillPane()));
	}

	@FXML
	private void manageDiscount() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageDiscountPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		managePositionTable();
	}
}
