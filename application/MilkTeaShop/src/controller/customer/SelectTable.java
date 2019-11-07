package controller.customer;

import app.secondary.SecondaryStage;
import component.controller.TablePane;
import app.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Table;

import java.net.URL;
import java.util.ResourceBundle;

class SelectTable implements Controller, Initializable {
	@FXML
	private VBox tableComponent;

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
				SecondaryStage.getInstance().setScene("/view/customer/PayBill.fxml", "/style/customer/PayBill.css", new PayBill());
			}
		};
		tableComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/TablePane.fxml", tablePane));
	}
}
