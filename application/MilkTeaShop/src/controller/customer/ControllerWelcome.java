package controller.customer;

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
public final class ControllerWelcome implements Initializable {

    @FXML
    private void login() {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewLogin.fxml"));
            view.setController(new ControllerCustomerLogin());
            DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
            DialogSecondaryStage.getInstance().getStage().show();
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    @FXML
    private void register() {
        try {
            // Set up view Register for customer on dialog Stage and show it
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
            view.setController(new ControllerRegister());
            DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
            DialogSecondaryStage.getInstance().getStage().show();
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    @FXML
    private void order() {
        try {
            // Set up view ControllerOrder for customer on secondary Stage
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewOrder.fxml"));
            SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
            SecondaryStage.getInstance().setOrdering(true);
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            WarningAlert.getInstance().showAndWait("Fail!", "You do not have permission to do this action!");
        });
    }
}
