package controller.customer;

import access.AccessAccount;
import controller.Information;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import library.WarningAlert;
import main.DialogStage;
import main.SecondaryStage;
import model.Account;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Register for Customer.
 */
public final class Register extends Information implements Initializable {
    @Override
    protected void submit(ActionEvent event) {
        StringBuilder name = new StringBuilder();
        for (String word : getName().split(" ")) {
            name.append(word.charAt(0));
        }
        Account account = AccessAccount.getInstance().insert(
                name.toString().toLowerCase(), getName(), getMale(), getBirthday(), getAddress(), getPhone(), getEmail(), 1);
        if (account != null) {
            SecondaryStage.getInstance().setAccount(account);
            DialogStage.getInstance().getStage().close();
        } else {
            WarningAlert.getInstance().showAndWait(
                    "Register failed", "Can not insert Customer's Information");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle("REGISTER");
        SecondaryStage.getInstance().getStage().hide();
        DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
