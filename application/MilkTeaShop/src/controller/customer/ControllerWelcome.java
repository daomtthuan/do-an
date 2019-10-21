package controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.DialogStage;
import main.SecondaryStage;
import library.ErrorAlert;

import java.io.IOException;

/**
 * The type Controller welcome.
 */
public final class ControllerWelcome {

    @FXML
    private void login(ActionEvent event) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewLogin.fxml"));
            view.setController(new ControllerCustomerLogin());
            DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
            DialogStage.getInstance().getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void register(ActionEvent event) {
        try {
            // Set up view Register for customer on dialog Stage and show it
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
            view.setController(new ControllerRegister());
            DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
            DialogStage.getInstance().getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void order(ActionEvent event) {
        try {
            // Set up view ControllerOrder for customer on secondary Stage
            ControllerOrder controllerOrder = new ControllerOrder();
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewOrder.fxml"));
            view.setController(controllerOrder);
            SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }
}
