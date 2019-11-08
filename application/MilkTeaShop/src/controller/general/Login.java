package controller.general;

import app.alert.AlertWarning;
import app.primary.PrimaryStage;
import app.secondary.SecondaryStage;
import app.Controller;
import controller.customer.Customer;
import controller.employee.Employee;
import controller.manager.Manager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import tool.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Controller, Initializable {
	@FXML
	private TextField accountTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private Button submitButton;

	protected String getAccount() {
		return accountTextField.getText();
	}

	protected String getPassword() {
		return passwordTextField.getText();
	}

	protected Button getSubmitButton() {
		return submitButton;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		accountTextField.setText("ttl3");
		passwordTextField.setText("1");
		getSubmitButton().setOnAction(actionEvent -> {
			if (getAccount().matches(Regex.ACCOUNT) && getPassword().matches(Regex.PASSWORD)) {
				Account account = api.Account.getInstance().login("Admin", getAccount(), getPassword());
				if (account != null) {
					if (account.getRoll() == 2) {
						PrimaryStage.getInstance().setScene("/view/employee/Employee.fxml", new Employee());
						PrimaryStage.getInstance().setAccount(account);

						SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css", new Customer());
						SecondaryStage.getInstance().getStage().show();
					}
					else {
						PrimaryStage.getInstance().setScene("/view/manager/Manager.fxml", new Manager());
						PrimaryStage.getInstance().setAccount(account);
					}
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
			}
		});
	}
}
