package controller.customer;

import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Customer implements Controller, Initializable {
	@FXML
	private void login() {
		DialogSecondaryStage.getInstance().setScene("/view/Login.fxml", new Login());
		DialogSecondaryStage.getInstance().getStage().show();
		SecondaryStage.getInstance().getStage().hide();
	}

	@FXML
	private void register() {
		DialogSecondaryStage.getInstance().setScene("/view/Account.fxml", "/style/general/Account.css", new Register());
		DialogSecondaryStage.getInstance().getStage().show();
		SecondaryStage.getInstance().getStage().hide();
	}

	@FXML
	private void order() {
		SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		SecondaryStage.getInstance().getStage().setOnCloseRequest(Event::consume);
	}
}
