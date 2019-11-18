package controller.customer;

import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.pattern.Controller;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Account;
import tool.Input;
import tool.Regex;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Register implements Controller {
	@FXML
	private TextField nameTextField;
	@FXML
	private RadioButton maleRadioButton;
	@FXML
	private RadioButton femaleRadioButton;
	@FXML
	private DatePicker birthdayDatePicker;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField phoneTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField rePasswordField;

	@FXML
	private void submit() {
		String name = Input.fixString(nameTextField.getText());
		boolean gender = maleRadioButton.isSelected();
		String birthday = birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String address = Input.fixString(addressTextField.getText());
		String phone = phoneTextField.getText();
		String email = emailTextField.getText().toLowerCase();
		String password = passwordField.getText();
		String re = rePasswordField.getText();

		if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL) && (maleRadioButton.isSelected() || femaleRadioButton.isSelected()) &&
				password.matches(Regex.PASSWORD) && re.matches(Regex.PASSWORD) && email.length() <= 50) {
			if (password.equals(re)) {
				Account account = api.Account.getInstance().insert(Input.createAcronym(name), password, 1, name, gender, birthday, address, phone, email);

				if (account != null) {
					SecondaryStage.getInstance().setAccount(account);

					if (!SecondaryStage.getInstance().isOrdering()) {
						SecondaryStage.getInstance().setBillDetails(new ArrayList<>());
						SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
					}

					AlertInformation.getInstance().showAndWait("Success!",
							"Your Customer Account: " + account.getAccount() + "\nThanks for coming to our shop!");
					SecondaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot register.\nPlease notify staff.");
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Re-Password not match.\nPlease check again.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
		}
	}
}
