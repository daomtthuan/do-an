package controller.customer;

import api.ApiDiscount;
import app.DialogSecondaryStage;
import app.SecondaryStage;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Discount;
import plugin.alert.AlertInformation;
import plugin.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public final class ControllerEnterDiscount implements Controller, Initializable {
    @FXML
    private TextField name;

    @FXML
    private void submit() {
        if (name.getText().matches("^.{1,50}$")) {
            Discount discount = ApiDiscount.getInstance().check(name.getText());
            if (discount != null) {
                SecondaryStage.getInstance().setDiscount(discount);
                AlertInformation.getInstance().showAndWait("Success!", "Your bill will be discounted " + discount.getSale() + "%.");
                DialogSecondaryStage.getInstance().getStage().hide();
                SecondaryStage.getInstance().getStage().show();
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Discount Code is incorrect.\nPlease check again.");
            }
        } else {
            AlertWarning.getInstance().showAndWait("Fail!", "Discount Code is incorrect.\nPlease check again.");
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
