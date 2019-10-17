package controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;
import model.Account;
import ui.ErrorAlert;
import ui.WarningAlert;

import java.io.IOException;

public class Login extends controller.Login {
    @Override
    public void login(ActionEvent event) {
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = access.Account.getInstance().login(getName(), getPassword());
            if (account != null) {
                try {
                    // Set primary stage for employee
                    FXMLLoader primaryView = new FXMLLoader(this.getClass().getResource("/view/employee/Main.fxml"));
                    Main.getPrimaryStage().setScene(new Scene(primaryView.load()));

                    // Set secondary stage for customer
                    FXMLLoader secondaryView = new FXMLLoader(this.getClass().getResource("/view/customer/Welcome.fxml"));
                    Main.setSecondaryStage(new Stage());
                    Main.getSecondaryStage().setTitle("Milk Tea Shop");
                    Main.getSecondaryStage().setScene(new Scene(secondaryView.load()));
                    Main.getSecondaryStage().setResizable(false);
                    Main.getSecondaryStage().show();
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
