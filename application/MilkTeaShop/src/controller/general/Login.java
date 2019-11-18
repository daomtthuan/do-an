package controller.general;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryStage;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import controller.customer.Customer;
import controller.customer.Order;
import controller.employee.Employee;
import controller.manager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import tool.Regex;

import java.util.ArrayList;

public class Login implements Controller {
	@FXML
	private TextField accountTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private Button submitButton;
	private String who;

	public Login(String who) {
		this.who = who;
	}

	@FXML
	private void submit() {
		if (accountTextField.getText().matches(Regex.ACCOUNT) && passwordTextField.getText().matches(Regex.PASSWORD)) {
			Account account = api.Account.getInstance().login(who, accountTextField.getText(), passwordTextField.getText());
			if (account != null) {
				if (who.equals("Admin")) {
					if (account.getRoll() == 2) {
						PrimaryStage.getInstance().setScene("/view/employee/Employee.fxml", "/style/employee/Employee.css", new Employee());
						PrimaryStage.getInstance().setAccount(account);

						SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css", new Customer());
						SecondaryStage.getInstance().getStage().show();
					} else if (account.getRoll() == 3) {
						PrimaryStage.getInstance().setScene("/view/manager/Manager.fxml", "/style/manager/Manager.css", new Manager());
						PrimaryStage.getInstance().setAccount(account);
					}
				} else if (who.equals("Customer")) {
					SecondaryStage.getInstance().setAccount(account);
					if (!SecondaryStage.getInstance().isOrdering()) {
						SecondaryStage.getInstance().setBillDetails(new ArrayList<>());
						SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
					}
					SecondaryDialog.getInstance().close();
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
		}
	}
}
