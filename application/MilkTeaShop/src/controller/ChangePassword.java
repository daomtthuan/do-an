package controller;

import api.AccountApi;
import app.stage.DialogPrimaryStage;
import app.stage.DialogSecondaryStage;
import app.stage.PrimaryStage;
import app.stage.SecondaryStage;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Account;
import org.jetbrains.annotations.Contract;
import app.string.Regex;
import app.alert.AlertInformation;
import app.alert.AlertWarning;

public class ChangePassword implements Controller {
    private Account account;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField repeatPasswordField;

    @Contract(pure = true)
    public ChangePassword(Account account) {
        this.account = account;
    }

    @FXML
    private void submit() {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String repeatPassword = repeatPasswordField.getText();

        if (oldPassword.matches(Regex.PASSWORD) && newPassword.matches(Regex.PASSWORD) && repeatPassword.matches(Regex.PASSWORD)) {
            if (oldPassword.equals(account.getPassword())) {
                if (newPassword.equals(repeatPassword)) {
                    Account newAccount = AccountApi.getInstance().update(account.getId(), newPassword);
                    if (newAccount != null) {
                        AlertInformation.getInstance().showAndWait("Success!", "Please remember your new password carefully.");
                        if (account.getRoll() == 1) {
                            SecondaryStage.getInstance().setAccount(newAccount);
                            DialogSecondaryStage.getInstance().getStage().close();
                        } else {
                            PrimaryStage.getInstance().setAccount(newAccount);
                            DialogPrimaryStage.getInstance().getStage().hide();
                            PrimaryStage.getInstance().getStage().show();
                        }
                    } else {
                        AlertWarning.getInstance().showAndWait("Fail!", "Can not change Password.\nPlease notify staff.");
                    }
                } else {
                    AlertWarning.getInstance().showAndWait("Fail!", "Repeat Password does not match.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Password is incorrect.");
            }
        } else {
            AlertWarning.getInstance().showAndWait("Fail!", "Password is incorrect.");
        }
    }
}
