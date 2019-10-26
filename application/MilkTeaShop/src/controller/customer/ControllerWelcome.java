package controller.customer;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import library.ErrorAlert;
import library.WarningAlert;
import main.DialogSecondaryStage;
import main.SecondaryStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller welcome.
 */
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
            WarningAlert.getInstance().showAndWait("Fail!", "You do not have permission to do this action!");
        });
    }
}
