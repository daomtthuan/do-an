package controller.customer;

import access.AccessDiscount;
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

public final class ControllerEnterDiscount implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private void submit() {
        if (name.getText().matches("^.{1,50}$")) {
            Discount discount = AccessDiscount.getInstance().check(name.getText());
            if (discount != null) {
                SecondaryStage.getInstance().setDiscount(discount);
                InformationAlert.getInstance().showAndWait("Enter Discount Code succeeded!", "Your bill will be discounted " + discount.getSale() + "%.");
                DialogSecondaryStage.getInstance().getStage().hide();
                SecondaryStage.getInstance().getStage().show();
            } else {
                WarningAlert.getInstance().showAndWait("Enter Discount Code failed!", "Discount Code is incorrect.\nPlease check again.");
            }
        }
        else {
            WarningAlert.getInstance().showAndWait("Enter Discount Code failed!", "Discount Code is incorrect.\nPlease check again.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().hide();
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
