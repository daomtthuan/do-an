package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import component.controller.managepane.ManageFoodPane;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import model.Category;
import model.Food;
import tool.Input;
import tool.Regex;

import java.net.URL;
import java.util.ResourceBundle;

public class EditFood implements Controller, Initializable {
	@FXML
	private ComboBox<Category> categoryComboBox;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField priceTextField;
	private model.Food food;
	private boolean edit;
	private ObservableList<Category> categories;
	private SingleSelectionModel<Category> category;
	private ManageFoodPane manageFoodPane;

	public EditFood(ManageFoodPane manageFoodPane, ObservableList<Category> categories, SingleSelectionModel<Category> category) {
		this.manageFoodPane = manageFoodPane;
		this.categories = categories;
		this.category = category;
		edit = false;
	}

	public EditFood(ManageFoodPane manageFoodPane, ObservableList<Category> categories, SingleSelectionModel<Category> category, Food food) {
		this.manageFoodPane = manageFoodPane;
		this.categories = categories;
		this.category = category;
		this.food = food;
		edit = true;
	}

	@FXML
	private void submit() {
		String name = Input.fixString(nameTextField.getText());
		String price = priceTextField.getText();
		if (name.matches(Regex.NAME) && price.matches(Regex.MONEY)) {
			if (edit) {
				if (api.Food.getInstance().update(category.getSelectedItem().getId(), name, categoryComboBox.getSelectionModel().getSelectedItem().getId(), Double.parseDouble(price)) != null) {
					manageFoodPane.refresh();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not update food.\nPlease check again.");
				}
			} else {
				if (api.Food.getInstance().insert(name, categoryComboBox.getSelectionModel().getSelectedItem().getId(), Double.parseDouble(price)) != null) {
					manageFoodPane.refresh();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Can not insert food.\nPlease check again.");
				}
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		categoryComboBox.setItems(categories);
		categoryComboBox.getSelectionModel().select(category.getSelectedIndex());
		if (edit) {
			nameTextField.setText(food.getName());
			priceTextField.setText(String.valueOf(food.getPrice()));
		}
	}
}
