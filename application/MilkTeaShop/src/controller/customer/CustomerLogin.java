package controller.customer;

import api.AccountApi;
import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.Controller;
import controller.Login;
import javafx.fxml.Initializable;
import model.Account;
import app.string.Regex;
import app.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public final class CustomerLogin extends Login implements Controller, Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSubmitButton().setDefaultButton(true);
        getSubmitButton().setOnAction(actionEvent -> {
            if (getAccount().matches(Regex.ACCOUNT) && getPassword().matches(Regex.PASSWORD)) {
                Account account = AccountApi.getInstance().login("Customer", getAccount(), getPassword());
                if (account != null) {
                    SecondaryStage.getInstance().setAccount(account);
                    if (!SecondaryStage.getInstance().isOrdering()) {
                        SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css");
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
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
