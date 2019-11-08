package controller.customer;

import app.Controller;
import app.secondary.SecondaryStage;
import component.controller.TablePane;
import controller.employee.ManageOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Table;
import org.jetbrains.annotations.Contract;

import java.net.URL;
import java.util.ResourceBundle;

class SelectTable implements Controller, Initializable {
	@FXML
	private VBox tableComponent;
	private ManageOrder manageOrder;

	@Contract(pure = true)
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
			public void selectTable(Table table) {
				SecondaryStage.getInstance().setTable(table);
				manageOrder.getBillPane().setTable(table.toString());
				SecondaryStage.getInstance().setScene("/view/customer/PayBill.fxml", "/style/customer/PayBill.css", new PayBill());
			}
		};
		tableComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/TablePane.fxml", tablePane));
	}
}
