package controller.employee;

import app.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import app.secondary.SecondaryStage;
import component.controller.BillPane;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageOrder implements Controller, Initializable {
	@FXML
	private Button payBillButton;
	@FXML
	private Button customerButton;
	@FXML
	private VBox billComponent;
	private BillPane billPane;

	public BillPane getBillPane() {
		return billPane;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		billPane = new BillPane() {
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
		setupCustomerButton();
		setupPayBillButton(true);

		PrimaryDialog.getInstance().getStage().setOnCloseRequest(Event::consume);
	}

	public void setupPayBillButton(boolean isDisable) {
		payBillButton.setDisable(isDisable);
	}

	public void setupCustomerButton() {
		customerButton.setDisable(SecondaryStage.getInstance().getAccount() == null);
	}
}
