package controller;

import access.AccessAccount;
import javafx.event.ActionEvent;
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
public class ControllerAdminLogin {
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
     *
     * @param event the event
     */
    @FXML
    protected void login(ActionEvent event) {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = AccessAccount.getInstance().login("Admin", getName(), getPassword());
            // If login success
            if (account != null) {
                try {
                    // Setup primary stage for employee
                    FXMLLoader primaryView = new FXMLLoader(getClass().getResource("/view/employee/ViewEmployee.fxml"));
                    PrimaryStage.getInstance().getStage().setScene(new Scene(primaryView.load()));
                    PrimaryStage.getInstance().setAccount(account);

                    // Setup secondary stage for customer and show it
                    FXMLLoader secondaryView = new FXMLLoader(getClass().getResource("/view/customer/ViewWelcome.fxml"));
                    SecondaryStage.getInstance().getStage().setScene(new Scene(secondaryView.load()));
                    SecondaryStage.getInstance().getStage().show();
                 //   SecondaryStage.getInstance().setAccount(null);
                } catch (IOException e) {
                    ErrorAlert.getInstance().showAndWait(e);
                }
            } else {
                WarningAlert.getInstance().showAndWait("Login failed", "Account or password is incorrect");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Login failed", "Invalid account");
        }

    }
}
