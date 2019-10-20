package controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.PrimaryStage;
import main.SecondaryStage;
import model.Account;
import ui.ErrorAlert;
import ui.WarningAlert;

import java.io.IOException;

/**
 * Controller Login for Employee.
 */
public final class Login extends controller.Login {
    @Override
    protected void login(ActionEvent event) {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = access.Account.getInstance().login(getName(), getPassword());
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
