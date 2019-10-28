package controller.customer;

import app.DialogSecondaryStage;
import app.SecondaryStage;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import plugin.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public final class ControllerWelcome implements Controller, Initializable {

    @FXML
    private void login() {
        // Set up view Login for customer on dialog Stage and show it
        DialogSecondaryStage.getInstance().setScene("/view/ViewLogin.fxml", new ControllerCustomerLogin());
        DialogSecondaryStage.getInstance().getStage().show();
    }

    @FXML
    private void register() {
        // Set up view Register for customer on dialog Stage and show it
        DialogSecondaryStage.getInstance().setScene("/view/ViewInformation.fxml", new ControllerRegister());
        DialogSecondaryStage.getInstance().getStage().show();
    }

    @FXML
    private void order() {
        // Set up view ControllerOrder for customer on secondary Stage
        SecondaryStage.getInstance().setScene("/view/customer/ViewOrder.fxml");
        SecondaryStage.getInstance().setOrdering(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Event on close stage
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            AlertWarning.getInstance().showAndWait("Fail!", "You do not have permission to do this action!");
        });
    }
}
