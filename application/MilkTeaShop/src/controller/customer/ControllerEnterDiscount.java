package controller.customer;

import access.AccessDiscount;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import library.InformationAlert;
import library.WarningAlert;
import main.DialogSecondaryStage;
import main.SecondaryStage;
import model.Discount;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller enter discount.
 */
public final class ControllerEnterDiscount implements Controller, Initializable {
    @FXML
    private TextField name;

    @FXML
    private void submit() {
        if (name.getText().matches("^.{1,50}$")) {
            Discount discount = AccessDiscount.getInstance().check(name.getText());
            if (discount != null) {
                SecondaryStage.getInstance().setDiscount(discount);
                InformationAlert.getInstance().showAndWait("Success!", "Your bill will be discounted " + discount.getSale() + "%.");
                DialogSecondaryStage.getInstance().getStage().hide();
                SecondaryStage.getInstance().getStage().show();
            } else {
                WarningAlert.getInstance().showAndWait("Fail!", "Discount Code is incorrect.\nPlease check again.");
            }
        } else {
            WarningAlert.getInstance().showAndWait("Fail!", "Discount Code is incorrect.\nPlease check again.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().hide();
        // Event On Close stage
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
