package controller;

import access.AccessAccount;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import library.WarningAlert;
import main.DialogStage;
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
        if (oldPassword.getText().matches("^\\w{1,50}$") &&
                newPassword.getText().matches("^\\w{1,50}$") &&
                repeatNewPassword.getText().matches("^\\w{1,50}$")) {
            if (oldPassword.getText().equals(account.getPassword())) {
                if (newPassword.getText().equals(repeatNewPassword.getText())) {
                    Account newAccount = AccessAccount.getInstance().update(account.getId(), newPassword.getText());
                    if (newAccount != null) {
                        if (account.getRoll() == 1) {
                            SecondaryStage.getInstance().setAccount(newAccount);
                            DialogStage.getInstance().getStage().hide();
                            SecondaryStage.getInstance().getStage().show();
                        } else {
                            PrimaryStage.getInstance().setAccount(newAccount);
                            DialogStage.getInstance().getStage().hide();
                            PrimaryStage.getInstance().getStage().show();
                        }
                    } else {
                        WarningAlert.getInstance().showAndWait("Change Password failed!", "Can not change Password.\nPlease notify staff.");
                    }
                } else {
                    WarningAlert.getInstance().showAndWait("Change Password failed!", "Repeat Password does not match.");
                }
            } else {
                WarningAlert.getInstance().showAndWait("Change Password failed!", "Password is incorrect.");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Change Password failed!", "Password is incorrect.");
        }
    }
}
