package controller.customer;

import access.AccessAccount;
import controller.AdminLogin;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import library.WarningAlert;
import main.DialogStage;
import main.SecondaryStage;
import model.Account;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Customer login.
 */
public final class CustomerLogin extends AdminLogin implements Initializable {
    @Override
    protected void login(ActionEvent event) {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = AccessAccount.getInstance().login("Customer", getName(), getPassword());
            // If login success
            if (account != null) {
                SecondaryStage.getInstance().setAccount(account);
                DialogStage.getInstance().getStage().close();
            } else {
                WarningAlert.getInstance().showAndWait("Login failed", "Account or password is incorrect");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Login failed", "Invalid account");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().hide();
        DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
