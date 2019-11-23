package controller.customer;

import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import tool.Regex;
import app.pattern.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Discount;

import java.net.URL;
import java.util.ResourceBundle;

class EnterDiscount implements Controller, Initializable {
	@FXML
	private TextField nameTextField;

	@FXML
	private void submit() {
		if (nameTextField.getText().matches(Regex.DISCOUNT)) {
			Discount discount = api.Discount.getInstance().check(nameTextField.getText());
			if (discount != null) {
				SecondaryStage.getInstance().setDiscount(discount);
				AlertInformation.getInstance().showAndWait("Success!", "Your bill will be discounted " + discount.getSale() + "%.");
				SecondaryDialog.getInstance().close();
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
