package controller.customer;

import app.Controller;
import app.primary.PrimaryStage;
import app.secondary.SecondaryStage;
import component.controller.BillPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

class PayBill implements Controller, Initializable {
	@FXML
	private VBox billComponent;

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
		billComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillPane.fxml", billPane));
	}
}
