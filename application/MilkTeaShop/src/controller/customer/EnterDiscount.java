package controller.customer;

import api.DiscountApi;
import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Discount;
import app.string.Regex;
import app.alert.AlertInformation;
import app.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public class EnterDiscount implements Controller, Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private void submit() {
        if (nameTextField.getText().matches(Regex.DISCOUNT)) {
            Discount discount = DiscountApi.getInstance().check(nameTextField.getText());
            if (discount != null) {
                SecondaryStage.getInstance().setDiscount(discount);
                AlertInformation.getInstance().showAndWait("Success!", "Your bill will be discounted " + discount.getSale() + "%.");
                DialogSecondaryStage.getInstance().getStage().close();
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
    }
}
