package controller.customer;

import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import app.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Customer implements Controller, Initializable {
	public Customer() {
		SecondaryStage.getInstance().setAccount(null);
		SecondaryStage.getInstance().setDiscount(null);
		SecondaryStage.getInstance().setBillDetails(null);
		SecondaryStage.getInstance().setTable(null);
	}

	@FXML
	private void login() {
		SecondaryDialog.getInstance().setScene("/view/general/Login.fxml", new Login());
		SecondaryDialog.getInstance().getStage().show();
		SecondaryStage.getInstance().getStage().hide();
	}

	@FXML
	private void register() {
		SecondaryDialog.getInstance().setScene("/view/general/Account.fxml", "/style/general/Account.css", new Register());
		SecondaryDialog.getInstance().getStage().show();
		SecondaryStage.getInstance().getStage().hide();
	}

	@FXML
	private void order() {
		SecondaryStage.getInstance().setBillDetails(new ArrayList<>());
		SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		SecondaryStage.getInstance().getStage().setOnCloseRequest(Event::consume);
	}
}
