package controller.employee;

import app.alert.AlertError;
import app.pattern.Controller;
import app.secondary.SecondaryStage;
import component.controller.general.BillPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Bill;
import model.Table;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ManageBillDetail implements Controller, Initializable {
	@FXML
	private VBox billComponent;
	private Table table;

	public ManageBillDetail(Table table) {
		this.table = table;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Bill bill = api.Bill.getInstance().getNotCheckoutBill(table.getId());

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
