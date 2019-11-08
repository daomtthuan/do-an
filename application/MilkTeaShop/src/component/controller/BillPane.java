package component.controller;

import app.Controller;
import app.secondary.SecondaryStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Account;
import model.BillDetail;
import model.Discount;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class BillPane implements Controller, Initializable {
	@FXML
	private GridPane billInformationPane;
	@FXML
	private Label checkinNameLabel;
	@FXML
	private Label checkoutNameLabel;
	@FXML
	private Label tableLabel;
	@FXML
	private Label customerLabel;
	@FXML
	private Label employeeLabel;
	@FXML
	private Label discountLabel;
	@FXML
	private Label checkinLabel;
	@FXML
	private Label checkoutLabel;
	@FXML
	private VBox billDetailComponent;
	private BillDetailPane billDetailPane;

	public void setTable(String text) {
		this.tableLabel.setText(text);
	}

	public void setCustomer(String text) {
		this.customerLabel.setText(text);
	}

	protected void setEmployee(String text) {
		this.employeeLabel.setText(text);
	}

	public void setDiscount(String text) {
		this.discountLabel.setText(text);
	}

	public void setCheckin(String text) {
		this.checkinLabel.setText(text);
	}

	public void setCheckout(String text) {
		this.checkoutLabel.setText(text);
	}

	public abstract void setup();

	public void setBillDetails(Account account, Discount discount, ArrayList<BillDetail> billDetails) {
		billDetailPane.setBillDetails(account, discount, billDetails);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		billDetailPane = new BillDetailPane();
		billDetailComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillDetailPane.fxml", billDetailPane));
		setup();
	}

	protected void removeCheck() {
		billInformationPane.getChildren().remove(checkinNameLabel);
		billInformationPane.getChildren().remove(checkinLabel);
		billInformationPane.getChildren().remove(checkoutNameLabel);
		billInformationPane.getChildren().remove(checkoutLabel);
	}
}
