package controller;

import api.AccountApi;
import app.stage.PrimaryStage;
import app.stage.SecondaryStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import app.string.Regex;
import app.alert.AlertWarning;

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
        submitButton.setDefaultButton(true);
        submitButton.setOnAction(actionEvent -> {
            if (getAccount().matches(Regex.ACCOUNT) && getPassword().matches(Regex.PASSWORD)) {
                Account account = AccountApi.getInstance().login("Admin", getAccount(), getPassword());
                if (account != null) {
                    PrimaryStage.getInstance().setScene("/view/employee/Employee.fxml");
                    PrimaryStage.getInstance().setAccount(account);

                    SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css");
                    SecondaryStage.getInstance().getStage().show();
                } else {
                    AlertWarning.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
            }
        });
    }
}
