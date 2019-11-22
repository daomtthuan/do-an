package controller.manager;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import component.controller.general.MenuPane;
import component.controller.general.managepane.ManageFoodPane;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
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
	private int selectedIndex;
	private Category selectedCategory;
	private Runnable refresh;

	public EditFood(ManageFoodPane manageFoodPane, ObservableList<Category> categories, SingleSelectionModel<Category> category) {
		this.refresh = manageFoodPane::refresh;
		this.categories = categories;
		this.selectedIndex = category.getSelectedIndex();
		this.selectedCategory = category.getSelectedItem();
		edit = false;
	}

	public EditFood(ManageFoodPane manageFoodPane, ObservableList<Category> categories, SingleSelectionModel<Category> category, Food food) {
		this.refresh = manageFoodPane::refresh;
		this.categories = categories;
		this.selectedIndex = category.getSelectedIndex();
		this.selectedCategory = category.getSelectedItem();
		this.food = food;
		edit = true;
	}

	public EditFood(MenuPane manageMenuPane, ArrayList<Category> categories, int selectedIndex, Category selectedCategory) {
		this.refresh = manageMenuPane.getRefresh();
		this.categories = FXCollections.observableArrayList(categories);
		this.selectedIndex = selectedIndex;
		this.selectedCategory = selectedCategory;
		edit = false;
	}

	public EditFood(MenuPane manageMenuPane, ArrayList<Category> categories, int selectedIndex, Category selectedCategory, Food food) {
		this.refresh = manageMenuPane.getRefresh();
		this.categories = FXCollections.observableArrayList(categories);
		this.selectedIndex = selectedIndex;
		this.selectedCategory = selectedCategory;
		this.food = food;
		edit = true;
	}

	public EditFood(MenuPane manageMenuPane, ArrayList<Category> categories) {
		this.refresh = manageMenuPane.getRefresh();
		this.categories = FXCollections.observableArrayList(categories);
		this.selectedIndex = -1;
		edit = false;
	}

	@FXML
	private void submit() {
		String name = Input.fixString(nameTextField.getText());
		String price = priceTextField.getText();
		if (!categoryComboBox.getSelectionModel().isEmpty() && name.matches(Regex.NAME) && price.matches(Regex.NUMBER)) {
			if (edit) {
				if (api.Food.getInstance().update(food.getId(), name, categoryComboBox.getSelectionModel().getSelectedItem().getId(), Double.parseDouble(price)) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot update food.\nPlease check again.");
				}
			} else {
				if (api.Food.getInstance().insert(name, categoryComboBox.getSelectionModel().getSelectedItem().getId(), Double.parseDouble(price)) != null) {
					refresh.run();
					PrimaryDialog.getInstance().close();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot insert food.\nPlease check again.");
				}
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		categoryComboBox.setItems(categories);
		if (selectedIndex > -1) {
			categoryComboBox.getSelectionModel().select(selectedIndex);
		}
		if (edit) {
			nameTextField.setText(food.getName());
			priceTextField.setText(String.valueOf(food.getPrice()));
		}
	}
}
