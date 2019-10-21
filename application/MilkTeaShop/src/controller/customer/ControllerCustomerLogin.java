package controller.customer;

import access.AccessAccount;
import controller.ControllerAdminLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import library.ErrorAlert;
import library.WarningAlert;
import main.DialogStage;
import main.SecondaryStage;
import model.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller customer login.
 */
public final class ControllerCustomerLogin extends ControllerAdminLogin implements Initializable {
    @Override
    protected void login(ActionEvent event) {
        // Check validate input account name and password
        if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
            Account account = AccessAccount.getInstance().login("Customer", getName(), getPassword());
            // If login success
            if (account != null) {
                SecondaryStage.getInstance().setAccount(account);
                try {
                    // Set up view ControllerOrder for customer on secondary Stage
                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewOrder.fxml"));
                    SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogStage.getInstance().getStage().hide();
                    SecondaryStage.getInstance().getStage().show();
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
