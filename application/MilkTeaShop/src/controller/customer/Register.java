package controller.customer;

import api.AccountApi;
import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import app.string.Regex;
import app.string.Tool;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class Register implements Controller {
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
        String name = Tool.fixString(nameTextField.getText());
        boolean gender = maleRadioButton.isSelected();
        String birthday = birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String address = Tool.fixString(addressTextField.getText());
        String phone = phoneTextField.getText();
        String email = emailTextField.getText().toLowerCase();

        if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL) && (maleRadioButton.isSelected() || femaleRadioButton.isSelected())) {
            model.Account account = AccountApi.getInstance().insert(Tool.createAcronym(name), 1, name, gender, birthday, address, phone, email);

            if (account != null) {
                SecondaryStage.getInstance().setAccount(account);

                if (!SecondaryStage.getInstance().isOrdering()) {
                    SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css");
                }

                AlertInformation.getInstance().showAndWait("Success!",
                        "Your Customer Account: " + account.getAccount() + "\nYour default Password: 1\nPlease change your Password at next Login times.\n" +
                                "Thanks for coming to our shop!");
                DialogSecondaryStage.getInstance().getStage().close();
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Can not register.\nPlease notify staff.");
            }
        } else {
            AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
        }
    }
}
