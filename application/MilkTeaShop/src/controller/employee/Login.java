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
        // Check input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = access.Account.getInstance().login(getName(), getPassword());
            // If login success
            if (account != null) {
                try {
                    // Set primary stage for employee
                    FXMLLoader primaryView = new FXMLLoader(this.getClass().getResource("/view/employee/Main.fxml"));
                    PrimaryStage.getInstance().setScene(new Scene(primaryView.load()));
                    PrimaryStage.getInstance().s

                    // Set secondary stage for customer
                    FXMLLoader secondaryView = new FXMLLoader(this.getClass().getResource("/view/customer/Welcome.fxml"));
                    SecondaryStage.getInstance().setScene(new Scene(secondaryView.load()));

                    // Show Secondary stage
                    //  Stage.getInstance().getSecondary().setFullScreen(true);
                    SecondaryStage.getInstance().show();
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
