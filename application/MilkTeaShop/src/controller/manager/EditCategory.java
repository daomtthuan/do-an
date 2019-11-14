package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import component.controller.manager.managepane.ManageCategoryPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import tool.Input;
import tool.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCategory implements Controller, Initializable {
	@FXML
	private TextField nameTextField;
	private model.Category category;
	private boolean edit;
	private ManageCategoryPane manageCategoryPane;

	public EditCategory(ManageCategoryPane manageCategoryPane) {
		this.manageCategoryPane = manageCategoryPane;
		edit = false;
	}

	public EditCategory(ManageCategoryPane manageCategoryPane, model.Category category) {
		this.category = category;
		this.manageCategoryPane = manageCategoryPane;
		edit = true;
	}

	@FXML
	private void submit() {
		String name = Input.fixString(nameTextField.getText());
		if (name.matches(Regex.NAME)) {
			if (edit) {
				if (api.Category.getInstance().update(category.getId(), name) != null) {
					manageCategoryPane.refresh();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not update category.\nExisted name category.");
				}
			} else {

				if (api.Category.getInstance().insert(name) != null) {
					manageCategoryPane.refresh();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not insert category.\nExisted name category.");
				}
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (edit) {
			nameTextField.setText(category.getName());
		}
	}
}
