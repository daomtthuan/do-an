package component.controller.manager.managepane;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import controller.manager.EditFood;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.Category;
import model.Food;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageFoodPane implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private ComboBox<Category> categoryComboBox;
	@FXML
	private TableView<Food> tableView;
	private TableColumn<Food, Integer> idColumn;
	private TableColumn<Food, String> nameColumn;
	private TableColumn<Food, Double> priceColumn;
	private TableColumn<Food, Boolean> enabledColumn;

	public ManageFoodPane() {
		idColumn = new TableColumn<>("Id Food");
		nameColumn = new TableColumn<>("Name Food");
		priceColumn = new TableColumn<>("Price Food");
		enabledColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableView.setPrefSize(1350, 610);
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(priceColumn);
		tableView.getColumns().add(enabledColumn);

		categoryComboBox.setItems(FXCollections.observableArrayList(api.Category.getInstance().getCategories()));
		categoryComboBox.setOnAction(actionEvent -> {
			tableView.getItems().clear();
			tableView.setItems(FXCollections.observableArrayList(api.Food.getInstance().getFoods(categoryComboBox.getSelectionModel().getSelectedItem().getId())));
		});

		MenuItem insertMenuItem = new MenuItem("Insert");
		insertMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/EditFood.fxml", new EditFood(this, categoryComboBox.getItems(), categoryComboBox.getSelectionModel()));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem updateMenuItem = new MenuItem("Update");
		updateMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/EditFood.fxml", new EditFood(this, categoryComboBox.getItems(), categoryComboBox.getSelectionModel(), tableView.getSelectionModel().getSelectedItem()));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem statusMenuItem = new MenuItem("Change status");
		statusMenuItem.setOnAction(actionEvent -> {
			Food food = tableView.getSelectionModel().getSelectedItem();
			if (api.Food.getInstance().changeStatus(food.getId(), !food.isEnabled()) != null) {
				refresh();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not change status.\nPlease notify staff.");
			}
		});

		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(actionEvent -> {
			Food food = tableView.getSelectionModel().getSelectedItem();
			if (api.Food.getInstance().delete(food.getId()) != null) {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not delete category.\nPlease notify staff.");
			} else {
				refresh();
			}
		});

		ContextMenu contextMenu = new ContextMenu(insertMenuItem, updateMenuItem, statusMenuItem, deleteMenuItem);
		tableView.setOnContextMenuRequested(contextMenuEvent -> {
			if (tableView.getSelectionModel().isEmpty()) {
				updateMenuItem.setDisable(true);
				statusMenuItem.setDisable(true);
				deleteMenuItem.setDisable(true);
			} else {
				updateMenuItem.setDisable(false);
				statusMenuItem.setDisable(false);
				deleteMenuItem.setDisable(false);
			}
			contextMenu.show(tableView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
		});
		tableView.setOnMouseClicked(mouseEvent -> {
			if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
				contextMenu.hide();
			}
		});

		categoryComboBox.getSelectionModel().select(0);
		tableView.setItems(FXCollections.observableArrayList(api.Food.getInstance().getFoods(categoryComboBox.getItems().get(0).getId())));
	}

	public void refresh() {
		tableView.getItems().clear();
		tableView.setItems(FXCollections.observableArrayList(api.Food.getInstance().getFoods(categoryComboBox.getSelectionModel().getSelectedItem().getId())));
	}
}
