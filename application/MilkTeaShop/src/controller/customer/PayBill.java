package controller.customer;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import app.secondary.SecondaryStage;
import component.controller.general.BillPane;
import controller.employee.ManageOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

class PayBill implements Controller, Initializable {
	@FXML
	private VBox billComponent;
	private ManageOrder manageOrder;

	PayBill(ManageOrder manageOrder) {
		this.manageOrder = manageOrder;
	}

	@FXML
	private void back() {
		if (SecondaryStage.getInstance().getBillDetails().size() > 0) {
			SecondaryStage.getInstance().setScene("/view/customer/SelectTable.fxml", "/style/customer/SelectTable.css", new SelectTable(manageOrder));
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		BillPane billPane = new BillPane() {
			@Override
			public void setup() {
				setTable(SecondaryStage.getInstance().getTable() != null ? SecondaryStage.getInstance().getTable().toString() : "-");
				setCustomer(SecondaryStage.getInstance().getAccount() != null ? SecondaryStage.getInstance().getAccount().toString() : "-");
				setEmployee(PrimaryStage.getInstance().getAccount().toString());
				setDiscount(SecondaryStage.getInstance().getDiscount() != null ? SecondaryStage.getInstance().getDiscount().getName() : "-");
				setBillDetails(SecondaryStage.getInstance().getAccount(), SecondaryStage.getInstance().getDiscount(), SecondaryStage.getInstance().getBillDetails());
				removeCheck();
			}
		};
		billComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/general/BillPane.fxml", billPane));
	}
}
