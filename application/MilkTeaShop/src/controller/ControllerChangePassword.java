package controller;

import api.ApiAccount;
import app.DialogPrimaryStage;
import app.DialogSecondaryStage;
import app.PrimaryStage;
import app.SecondaryStage;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Account;
import org.jetbrains.annotations.Contract;
import plugin.alert.AlertInformation;
import plugin.alert.AlertWarning;

public final class ControllerChangePassword implements Controller {
    private Account account;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField repeatNewPassword;

    @Contract(pure = true)
    public ControllerChangePassword(Account account) {
        this.account = account;
    }

    @FXML
    private void submit() {
        // check valid input
        if (oldPassword.getText().matches("^\\w{1,50}$") &&
                newPassword.getText().matches("^\\w{1,50}$") &&
                repeatNewPassword.getText().matches("^\\w{1,50}$")) {

            // check old password
            if (oldPassword.getText().equals(account.getPassword())) {

                // check matched password
                if (newPassword.getText().equals(repeatNewPassword.getText())) {
                    // update password
                    Account newAccount = ApiAccount.getInstance().update(account.getId(), newPassword.getText());

                    // check update success
                    if (newAccount != null) {
                        AlertInformation.getInstance().showAndWait("Success!", "Please remember your new password carefully.");

                        // check roll to update account for which stage?
                        if (account.getRoll() == 1) {
                            SecondaryStage.getInstance().setAccount(newAccount);
                            DialogSecondaryStage.getInstance().getStage().hide();
                            SecondaryStage.getInstance().getStage().show();
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
