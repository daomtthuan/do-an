package controller;

import access.AccessAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.PrimaryStage;
import main.SecondaryStage;
import library.ErrorAlert;
import library.WarningAlert;

import java.io.IOException;

/**
 * Abstract controller for view Login.
 */
public class Login {
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
     * Login. Event click button Login
     *
     * @param event the event
     */
    @FXML
    protected void login(ActionEvent event) {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            PrimaryStage.getInstance().setCursor(Cursor.WAIT);
            model.Account account = AccessAccount.getInstance().login("Admin", getName(), getPassword());
            PrimaryStage.getInstance().setCursor(Cursor.DEFAULT);
            // If login success
            if (account != null) {
                try {
                    // Setup primary stage for employee
                    FXMLLoader primaryView = new FXMLLoader(this.getClass().getResource("/view/employee/Main.fxml"));
                    PrimaryStage.getInstance().getStage().setScene(new Scene(primaryView.load()));
                    PrimaryStage.getInstance().setAccount(account);
                    // PrimaryStage.getInstance().s

                    // Setup secondary stage for customer and show it
                    FXMLLoader secondaryView = new FXMLLoader(this.getClass().getResource("/view/customer/Welcome.fxml"));
                    SecondaryStage.getInstance().getStage().setScene(new Scene(secondaryView.load()));
                    SecondaryStage.getInstance().getStage().show();
                    SecondaryStage.getInstance().setAccount(new model.Account());
                } catch (IOException e) {
                    ErrorAlert.getInstance().showAndWait(e);
                }
            } else {
                WarningAlert.getInstance().showAndWait("Login failed", "AccessAccount or password is incorrect");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Login failed", "Invalid account");
        }

    }
}
