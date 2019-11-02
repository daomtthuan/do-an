package controller.manager;

import api.AccountApi;
import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.stage.PrimaryStage;
import app.stage.SecondaryStage;
import app.string.Regex;
import app.string.Tool;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
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

    private model.Account account;

    @Contract(pure = true)
    public EditAccount(@NotNull model.Account account) {
        nameTextField.setText(account.getName());
        maleRadioButton.setSelected(account.isMale());
        femaleRadioButton.setSelected(account.isFemMale());
    }

    @FXML
    private void submit() {
        int id = account.getId();
        String name = Tool.fixString(nameTextField.getText());
        boolean gender = maleRadioButton.isSelected();
        String birthday = birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String address = Tool.fixString(addressTextField.getText());
        String phone = phoneTextField.getText();
        String email = emailTextField.getText().toLowerCase();

        if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL) && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
            model.Account account = AccountApi.getInstance().update(id, name, gender, birthday, address, phone, email);

            if (account != null) {
                // do some thing

            } else {
                // fail do some thing
            }
        } else {
            AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
        }
    }
}
