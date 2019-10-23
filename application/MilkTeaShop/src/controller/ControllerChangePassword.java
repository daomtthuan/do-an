package controller;

import access.AccessAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import library.WarningAlert;
import main.SecondaryStage;
import model.Account;

/**
 * The type Controller change password.
 */
public class ControllerChangePassword {
    private Account account;

    // setter account

    @FXML
    private Label title;
    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField repeatNewPassword;


    /**
     * Sets account.
     *
     * @param account the account
     */

    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     *
     * ChangePassword
     */
    @FXML
    protected void changePassword(){
        // Check validate input oldPassword ,newPassword and repeatNewPassword
        if (oldPassword.getText().matches("^\\w{1,50}$") && newPassword.getText().matches("^\\w{1,50}$") && repeatNewPassword.getText().matches("^\\w{1,50}$")){
            // oldPassword == account.getPassword() ???
            // newPassword == repeat ??
            if (oldPassword.getText().equals(account.getAccount())) {
                if (newPassword.getText().equals(repeatNewPassword)) {
                    Account account = AccessAccount.getInstance().update(this.account.getId(), newPassword.getText());
                }
            }
            // if change password success
            if (account != null){
                this.account = account;
            }
            else{
                WarningAlert.getInstance().showAndWait("New password and repeat new password not similar","Can not change new password \n Please notify staff");
            }
            WarningAlert.getInstance().showAndWait("New password and repeat new password not similar","Old password not to exist \n Please check again");
        }
    }

}
