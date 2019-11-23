package controller.manager;

import app.alert.AlertError;
import app.alert.AlertWarning;
import app.pattern.Controller;
import app.secondary.SecondaryStage;
import com.itextpdf.text.DocumentException;
import component.controller.general.BillPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Bill;
import tool.Printer;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ManageBillDetail implements Controller, Initializable {
	@FXML
	private VBox billComponent;
	private Bill bill;

	public ManageBillDetail(Bill bill) {
		this.bill = bill;
	}

	@FXML
	private void print() {
		try {
			Printer.printBill(bill);
		} catch (ParseException | IOException | DocumentException e) {
			AlertWarning.getInstance().showAndWait("Fail!", "Cannot print bill.\nPlease notify staff.");
		}
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
