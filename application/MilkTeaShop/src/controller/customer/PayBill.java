package controller.customer;

import app.stage.PrimaryStage;
import app.stage.SecondaryStage;
import component.controller.BillPane;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

class PayBill implements Controller, Initializable {
	@FXML
	private VBox billComponent;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		BillPane billPane = new BillPane() {
			@Override
			public void setup() {
				setTable(SecondaryStage.getInstance().getTable().toString());
				setCustomer(SecondaryStage.getInstance().getAccount() != null ? SecondaryStage.getInstance().getAccount().toString() : "Guest");
				setEmployee(PrimaryStage.getInstance().getAccount().toString());
				setDiscount(SecondaryStage.getInstance().getDiscount() != null ? SecondaryStage.getInstance().getDiscount().getName() : "-");
				setCheckin(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
				setCheckout("-");
			}
		};

		billComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillPane.fxml", billPane));
	}
}
