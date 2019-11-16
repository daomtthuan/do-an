package controller.manager;

import app.alert.AlertError;
import app.pattern.Controller;
import app.secondary.SecondaryStage;
import component.controller.general.BillPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Bill;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ManageBillDetail implements Controller, Initializable {
	@FXML
	private Button customerButton;
	@FXML
	private Button discountButton;
	@FXML
	private VBox billComponent;
	private Bill bill;

	public ManageBillDetail(Bill bill) {
		this.bill = bill;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		BillPane billPane = new BillPane() {
			@Override
			public void setup() {
				setTable(bill.getIdTable() + " - Table " + bill.getIdTable());
				setCustomer(bill.getIdCustomer() > 0 ? (bill.getIdCustomer() + " - " + bill.getNameCustomer()) : "-");
				setEmployee(bill.getIdEmployee() + " - " + bill.getNameEmployee());
				setDiscount(bill.getNameDiscount());
				try {
					setCheckin(bill.getCheckIn());
					setCheckout(bill.getCheckOut());
				} catch (ParseException e) {
					AlertError.getInstance().showAndWait(e);
				}
				setBillDetails(api.BillDetail.getInstance().getBillDetails(bill.getId()), bill.getTotalBefore(), bill.getSale(), bill.getTotal());
			}
		};
		billComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/general/BillPane.fxml", billPane));
	}
}
