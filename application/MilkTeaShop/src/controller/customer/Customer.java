package controller.customer;

import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public final class Customer implements Controller, Initializable {

    @FXML
    private void login() {
        DialogSecondaryStage.getInstance().setScene("/view/Login.fxml", new CustomerLogin());
        DialogSecondaryStage.getInstance().getStage().show();
    }

    @FXML
    private void register() {
        DialogSecondaryStage.getInstance().setScene("/view/Information.fxml", "/style/Information.css", new Register());
        DialogSecondaryStage.getInstance().getStage().show();
    }

    @FXML
    private void order() {
        SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css");
        SecondaryStage.getInstance().setOrdering(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().setOnCloseRequest(Event::consume);
    }
}
