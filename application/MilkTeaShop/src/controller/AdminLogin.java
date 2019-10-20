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
 * Abstract controller for view AdminLogin.
 */
public class AdminLogin {
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
     * AdminLogin. Event click button AdminLogin
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
                    FXMLLoader primaryView = new FXMLLoader(this.getClass().getResource("/view/employee/Main.fxml"));
                    PrimaryStage.getInstance().getStage().setScene(new Scene(primaryView.load()));
                    PrimaryStage.getInstance().setAccount(account);

                    // Setup secondary stage for customer and show it
                    FXMLLoader secondaryView = new FXMLLoader(this.getClass().getResource("/view/customer/Welcome.fxml"));
                    SecondaryStage.getInstance().getStage().setScene(new Scene(secondaryView.load()));
                    SecondaryStage.getInstance().getStage().show();
                    SecondaryStage.getInstance().setAccount(new Account());
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
