package controller;

import access.AccessAccount;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import library.InformationAlert;
import library.WarningAlert;
import main.DialogPrimaryStage;
import main.DialogSecondaryStage;
import main.PrimaryStage;
import main.SecondaryStage;
import model.Account;
import org.jetbrains.annotations.Contract;

/**
 * The type Controller change password.
 */
public final class ControllerChangePassword {
    private Account account;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField repeatNewPassword;

    /**
     * Instantiates a new Controller change password.
     *
     * @param account the account
     */
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
                    Account newAccount = AccessAccount.getInstance().update(account.getId(), newPassword.getText());

                    // check update success
                    if (newAccount != null) {
                        InformationAlert.getInstance().showAndWait("Success!", "Please remember your new password carefully.");

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
                        WarningAlert.getInstance().showAndWait("Fail!", "Can not change Password.\nPlease notify staff.");
                    }
                } else {
                    WarningAlert.getInstance().showAndWait("Fail!", "Repeat Password does not match.");
                }
            } else {
                WarningAlert.getInstance().showAndWait("Fail!", "Password is incorrect.");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Fail!", "Password is incorrect.");
        }
    }
}
