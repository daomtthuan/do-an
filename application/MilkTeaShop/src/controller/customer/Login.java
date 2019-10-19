package controller.customer;

import javafx.event.ActionEvent;
import main.SecondaryStage;
import model.Account;
import ui.WarningAlert;

public class Login extends controller.Login {
    @Override
    protected void login(ActionEvent event) {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = access.Account.getInstance().login(getName(), getPassword());
            // If login success
            if (account != null) {
                SecondaryStage.getInstance().setAccount(account);
            } else {
                WarningAlert.getInstance().showAndWait("Login failed", "Account or password is incorrect");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Login failed", "Invalid account");
        }
    }
}
