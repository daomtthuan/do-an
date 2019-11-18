package controller.customer;

import app.pattern.Controller;
import app.secondary.SecondaryStage;
import component.controller.general.TablePane;
import controller.employee.ManageOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Table;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

class SelectTable implements Controller, Initializable {
	@FXML
	private VBox tableComponent;
	private ManageOrder manageOrder;

	SelectTable(ManageOrder manageOrder) {
		this.manageOrder = manageOrder;
	}

	@FXML
	private void back() {
		SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		TablePane tablePane = new TablePane() {
			@Override
			public void setup() {
				ArrayList<Table> tables = api.Table.getInstance().getEnabledTables();
				tables.forEach(table -> {
					Button tableButton = createButton(table);
					if (table.isBusy()) {
						tableButton.getStyleClass().add("busy");
					} else {
						tableButton.setOnAction(actionEvent -> {
							SecondaryStage.getInstance().setTable(table);
							manageOrder.getBillPane().setTable(table.toString());
							SecondaryStage.getInstance().setScene("/view/customer/PayBill.fxml", "/style/customer/PayBill.css", new PayBill());
							manageOrder.setupPayBillButton(false);
						});
					}
					getTablePane().getChildren().add(tableButton);
				});
			}
		};
		tableComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/general/TablePane.fxml", tablePane));
	}
}
