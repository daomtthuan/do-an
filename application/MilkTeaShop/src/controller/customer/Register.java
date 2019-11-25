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

		if (name.matches(Regex.NAME)) {
			if (!birthday.equals("")) {
				if (address.matches(Regex.ADDRESS)) {
					if (phone.matches(Regex.PHONE)) {
						if (email.matches(Regex.EMAIL)) {
							if ((maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
								if (password.matches(Regex.PASSWORD)) {
									if (email.length() <= 50) {
										if (password.equals(re)) {
											Account account = api.Account.getInstance().insert(Input.createAcronym(name), password, 1, name, gender, birthday, address, phone, email);

											if (account != null) {
												SecondaryStage.getInstance().setAccount(account);

												if (SecondaryStage.getInstance().isNotOrdering()) {
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
										AlertWarning.getInstance().showAndWait("Fail!", "Invalid email.\nPlease check again.");
									}
								} else {
									AlertWarning.getInstance().showAndWait("Fail!", "Invalid password.\nPlease check again.");
								}
							} else {
								AlertWarning.getInstance().showAndWait("Fail!", "Please choose gender.");
							}
						} else {
							AlertWarning.getInstance().showAndWait("Fail!", "Invalid email.\nPlease check again.");
						}
					} else {
						AlertWarning.getInstance().showAndWait("Fail!", "Invalid phone.\nPlease check again.");
					}
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Invalid address.\nPlease check again.");
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Please enter birthday.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid name.\nPlease check again.");
		}
	}
}
