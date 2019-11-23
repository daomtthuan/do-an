package controller.employee;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import app.primary.PrimarySubDialog;
import app.secondary.SecondaryStage;
import com.itextpdf.text.DocumentException;
import component.controller.general.BillPane;
import controller.customer.Customer;
import controller.general.WatchAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Bill;
import model.BillDetail;
import tool.Printer;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
		billComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/general/BillPane.fxml", billPane));
		setupCustomerButton();
		setupPayBillButton(true);

		PrimaryDialog.getInstance().getStage().setOnCloseRequest(windowEvent -> {
			SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css", new Customer());
			PrimaryDialog.getInstance().getStage().hide();
		});
	}

	public void setupPayBillButton(boolean isDisable) {
		payBillButton.setDisable(isDisable);
	}

	public void setupCustomerButton() {
		customerButton.setDisable(SecondaryStage.getInstance().getAccount() == null);
	}

	@FXML
	private void watchCustomerAccount() {
		PrimarySubDialog.getInstance().setScene("/view/general/WatchAccount.fxml", "/style/general/Account.css", new WatchAccount(SecondaryStage.getInstance().getAccount()));
		PrimarySubDialog.getInstance().getStage().show();
	}

	@FXML
	private void payBill() {
		Bill bill = api.Bill.getInstance().insert(SecondaryStage.getInstance().getTable(), SecondaryStage.getInstance().getAccount(), PrimaryStage.getInstance().getAccount(), SecondaryStage.getInstance().getDiscount(), billPane.getSale());
		if (bill != null) {
			for (BillDetail billDetail : SecondaryStage.getInstance().getBillDetails()) {
				bill = api.BillDetail.getInstance().insert(bill.getId(), billDetail.getIdFood(), billDetail.getNameFood(), billDetail.getIdCategory(), billDetail.getNameCategory(), billDetail.getQuantity(), billDetail.getPrice());
				if (bill == null) {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot pay bill.\nPlease notify staff.");
					return;
				}
			}
			try {
				Printer.printBill(bill);
			} catch (ParseException | IOException | DocumentException e) {
				AlertWarning.getInstance().showAndWait("Fail!", "Cannot print bill.\nPlease notify staff.");
			}
			if (PrimaryStage.getInstance().getRefresh() != null) {
				PrimaryStage.getInstance().getRefresh().run();
			}
			SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css", new Customer());
			PrimaryDialog.getInstance().getStage().hide();
			PrimarySubDialog.getInstance().getStage().hide();
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Cannot pay bill.\nPlease notify staff.");
		}
	}
}
