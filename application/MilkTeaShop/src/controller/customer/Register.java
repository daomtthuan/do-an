package controller.customer;

import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Account;
import tool.Input;
import tool.Regex;

import java.time.format.DateTimeFormatter;

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
	private void submit() {
		java.lang.String name = Input.fixString(nameTextField.getText());
		boolean gender = maleRadioButton.isSelected();
		java.lang.String birthday = birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		java.lang.String address = Input.fixString(addressTextField.getText());
		java.lang.String phone = phoneTextField.getText();
		java.lang.String email = emailTextField.getText().toLowerCase();

		if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL) && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
			Account account = api.Account.getInstance().insert(Input.createAcronym(name), 1, name, gender, birthday, address, phone, email);

			if (account != null) {
				SecondaryStage.getInstance().setAccount(account);

				if (SecondaryStage.getInstance().isNotOrdering()) {
					SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order());
				}

				AlertInformation.getInstance().showAndWait("Success!",
						"Your Customer Account: " + account.getAccount() + "\nYour default Password: 1\nPlease change your Password at next Login times.\n" +
								"Thanks for coming to our shop!");
				DialogSecondaryStage.getInstance().close();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not register.\nPlease notify staff.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
		}
	}
}
