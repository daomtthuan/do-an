package controller.customer;

import access.AccessAccount;
import controller.Controller;
import controller.ControllerAdminLogin;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import library.ErrorAlert;
import library.WarningAlert;
import main.DialogSecondaryStage;
import main.SecondaryStage;
import model.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller customer login.
 */
public final class ControllerCustomerLogin extends ControllerAdminLogin implements Controller, Initializable {
    @Override
    protected void login() {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = AccessAccount.getInstance().login("Customer", getName(), getPassword());
            // If login success
            if (account != null) {
                SecondaryStage.getInstance().setAccount(account);
                // Set up view ControllerOrder for customer on secondary Stage
                if (!SecondaryStage.getInstance().isOrdering()) {
                    SecondaryStage.getInstance().setScene("/view/customer/ViewOrder.fxml");
                }
                DialogSecondaryStage.getInstance().getStage().hide();
                SecondaryStage.getInstance().getStage().show();
            } else {
                WarningAlert.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().hide();
        // Event On Close stage
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
