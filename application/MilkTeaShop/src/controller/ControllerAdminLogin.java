package controller;

import access.AccessAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import library.ErrorAlert;
import library.WarningAlert;
import main.PrimaryStage;
import main.SecondaryStage;
import model.Account;

import java.io.IOException;

/**
 * The type Controller admin login.
 */
public class ControllerAdminLogin implements Controller {
    @FXML
    private TextField name;
    @FXML
    private TextField password;

    /**
     * Gets name.
     *
     * @return the name
     */
    protected String getName() {
        return name.getText();
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    protected String getPassword() {
        return password.getText();
    }

    /**
     * Login.
     */
    @FXML
    protected void login() {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = AccessAccount.getInstance().login("Admin", getName(), getPassword());
            // If login success
            if (account != null) {
                // Setup primary stage for employee
                PrimaryStage.getInstance().setScene("/view/employee/ViewEmployee.fxml");
                PrimaryStage.getInstance().setAccount(account);

                // Setup secondary stage for customer and show it
                SecondaryStage.getInstance().setScene("/view/customer/ViewWelcome.fxml");
                SecondaryStage.getInstance().getStage().show();
                SecondaryStage.getInstance().setAccount(null);
            } else {
                WarningAlert.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
        }

    }
}
