package controller.manager;

import app.alert.AlertWarning;
import tool.Regex;
import tool.Input;
import app.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Account;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;

public class EditAccount implements Controller {
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
	private Account account;

	@Contract(pure = true)
	public EditAccount(@NotNull Account account) {
		nameTextField.setText(account.getName());
		maleRadioButton.setSelected(account.isMale());
		femaleRadioButton.setSelected(account.isFemMale());
	}

	@FXML
	private void submit() {
		int id = account.getId();
		java.lang.String name = Input.fixString(nameTextField.getText());
		boolean gender = maleRadioButton.isSelected();
		java.lang.String birthday = birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		java.lang.String address = Input.fixString(addressTextField.getText());
		java.lang.String phone = phoneTextField.getText();
		java.lang.String email = emailTextField.getText().toLowerCase();

		if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL) && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
		}
	}
}
