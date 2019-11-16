package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

		if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL) && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
			if (edit) {
				if (api.Account.getInstance().update(account.getId(), account.getPassword(), account.getRoll(), name, gender, birthday, address, phone, email) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not update account.\nlease notify staff.");
				}
			} else {
				if (api.Account.getInstance().insert(Input.createAcronym(name), "1", roll, name, gender, birthday, address, phone, email) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not insert account.\nlease notify staff.");
				}
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
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
