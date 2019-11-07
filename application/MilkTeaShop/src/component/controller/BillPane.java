package component.controller;

import app.secondary.SecondaryStage;
import app.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BillPane implements Controller, Initializable {
	@FXML
	private GridPane billInformationPane;
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

	protected void setTable(String text) {
		this.tableLabel.setText(text);
	}

	protected void setCustomer(String text) {
		this.customerLabel.setText(text);
	}

	protected void setEmployee(String text) {
		this.employeeLabel.setText(text);
	}

	protected void setDiscount(String text) {
		this.discountLabel.setText(text);
	}

	protected void setCheckin(String text) {
		this.checkinLabel.setText(text);
	}

	protected void setCheckout(String text) {
		this.checkoutLabel.setText(text);
	}

	public abstract void setup();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		BillDetailPane billDetailPane = new BillDetailPane() {
			@Override
			public void setup() {
				setBillDetails(SecondaryStage.getInstance().getBillDetails());
				setTotalBefore(SecondaryStage.getInstance().getTotalBefore());
				setSale(SecondaryStage.getInstance().getSale());
				setTotal(SecondaryStage.getInstance().getTotal());
			}
		};

		billDetailComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillDetailPane.fxml", billDetailPane));
		setup();
	}
}
