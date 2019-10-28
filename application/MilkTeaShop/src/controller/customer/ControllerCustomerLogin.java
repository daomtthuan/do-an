package controller.customer;

import api.ApiAccount;
import app.DialogSecondaryStage;
import app.SecondaryStage;
import controller.Controller;
import controller.ControllerLogin;
import javafx.fxml.Initializable;
import model.Account;
import plugin.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public final class ControllerCustomerLogin extends ControllerLogin implements Controller, Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSubmit().setDefaultButton(true);
        getSubmit().setOnAction(actionEvent -> {
            // Check validate input account name and password
            if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
                Account account = ApiAccount.getInstance().login("Customer", getName(), getPassword());
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
                    AlertWarning.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
            }
        });

        SecondaryStage.getInstance().getStage().hide();
        // Event On Close stage
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
