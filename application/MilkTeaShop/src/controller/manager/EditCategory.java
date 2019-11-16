package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import component.controller.general.MenuPane;
import component.controller.manager.managepane.ManageCategoryPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Category;
import tool.Input;
import tool.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCategory implements Controller, Initializable {
	@FXML
	private TextField nameTextField;
	private model.Category category;
	private boolean edit;
	private Runnable refresh;

	public EditCategory(ManageCategoryPane manageCategoryPane) {
		this.refresh = manageCategoryPane::refresh;
		edit = false;
	}

	public EditCategory(ManageCategoryPane manageCategoryPane, model.Category category) {
		this.refresh = manageCategoryPane::refresh;
		this.category = category;
		edit = true;
	}

	public EditCategory(MenuPane menuPane) {
		this.refresh = menuPane.getRefresh();
		edit = false;
	}

	public EditCategory(MenuPane menuPane, Category category) {
		this.refresh = menuPane.getRefresh();
		this.category = category;
		edit = true;
	}

	@FXML
	private void submit() {
		String name = Input.fixString(nameTextField.getText());
		if (name.matches(Regex.NAME)) {
			if (edit) {
				if (api.Category.getInstance().update(category.getId(), name) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not update category.\nExisted name category.");
				}
			} else {

				if (api.Category.getInstance().insert(name) != null) {
					refresh.run();
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
