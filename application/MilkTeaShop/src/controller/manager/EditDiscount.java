package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Discount;
import tool.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDiscount implements Controller, Initializable {
	@FXML
	private TextField saleTextField;
	private Runnable refresh;
	private Discount discount;
	private boolean edit;

	public EditDiscount(Runnable refresh) {
		this.refresh = refresh;
		edit = false;
	}

	public EditDiscount(Runnable refresh, Discount discount) {
		this.refresh = refresh;
		this.discount = discount;
		edit = true;
	}

	@FXML
	private void submit() {
		double sale = Double.parseDouble(saleTextField.getText());

		if (saleTextField.getText().matches(Regex.NUMBER) && 0 < sale && sale <= 100) {
			if (edit) {
				if (api.Discount.getInstance().update(discount.getId(), sale) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot update discount code.\nPlease notify staff.");
				}
			} else {
				if (api.Discount.getInstance().insert(sale) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot insert discount code.\nPlease notify staff.");
				}
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid sale number.\nPlease check again.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (edit) {
			saleTextField.setText(String.valueOf(discount.getSale()));
		}
	}
}
