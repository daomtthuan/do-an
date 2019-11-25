package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Account;
import tool.Input;
import tool.Regex;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditAccount implements Controller, Initializable {
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
	private int roll;
	private Runnable refresh;
	private model.Account account;
	private boolean edit;

	public EditAccount(Runnable refresh, int roll) {
		this.refresh = refresh;
		this.roll = roll;
		edit = false;
	}

	public EditAccount(Runnable refresh, model.Account account) {
		this.refresh = refresh;
		this.account = account;
		edit = true;
	}

	@FXML
	private void submit() {
		java.lang.String name = Input.fixString(nameTextField.getText());
		boolean gender = maleRadioButton.isSelected();
		java.lang.String birthday = birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		java.lang.String address = Input.fixString(addressTextField.getText());
		java.lang.String phone = phoneTextField.getText();
		java.lang.String email = emailTextField.getText().toLowerCase();

		if (name.matches(Regex.NAME)) {
			if (address.matches(Regex.ADDRESS)) {
				if (phone.matches(Regex.PHONE)) {
					if (email.matches(Regex.EMAIL)) {
						if ((maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
							if (edit) {
								Account newAccount = api.Account.getInstance().update(account.getId(), account.getPassword(), account.getRoll(), name, gender, birthday, address, phone, email);
								if (newAccount != null) {
									refresh.run();
									if (newAccount.getId() == PrimaryStage.getInstance().getAccount().getId()) {
										PrimaryStage.getInstance().setAccount(newAccount);
									}
									PrimaryDialog.getInstance().close();
								} else {
									AlertWarning.getInstance().showAndWait("Fail!", "Cannot update account.\nPlease notify staff.");
								}
							} else {
								if (api.Account.getInstance().insert(Input.createAcronym(name), "1", roll, name, gender, birthday, address, phone, email) != null) {
									refresh.run();
									PrimaryDialog.getInstance().close();
								} else {
									AlertWarning.getInstance().showAndWait("Fail!", "Cannot insert account.\nPlease notify staff.");
								}
							}
						} else {
							AlertWarning.getInstance().showAndWait("Fail!", "Please choose gender.");
						}
					} else {
						AlertWarning.getInstance().showAndWait("Fail!", "Invalid email.\n Please check again.");
					}
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Invalid phone.\n Please check again.");
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Invalid address.\n Please check again.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid name.\n Please check again.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (edit) {
			nameTextField.setText(account.getName());
			maleRadioButton.setSelected(account.isMale());
			femaleRadioButton.setSelected(account.isFemMale());
			birthdayDatePicker.setValue(LocalDate.parse(account.getBirthday()));
			addressTextField.setText(account.getAddress());
			phoneTextField.setText(account.getPhone());
			emailTextField.setText(account.getEmail());
		}
	}
}
