package component.controller.managepane;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import controller.manager.EditCategory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCategoryPane implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private TableView<Category> tableView;
	private TableColumn<Category, Integer> idColumn;
	private TableColumn<Category, String> nameColumn;
	private TableColumn<Category, Boolean> enabledColumn;

	public ManageCategoryPane() {
		idColumn = new TableColumn<>("Id Category");
		nameColumn = new TableColumn<>("Name Category");
		enabledColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameTableView.setText("Category List");
		tableView.setPrefSize(1350, 650);
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(enabledColumn);
		tableView.setItems(FXCollections.observableArrayList(api.Category.getInstance().getCategories()));

		MenuItem insertMenuItem = new MenuItem("Insert");
		insertMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/EditCategory.fxml", new EditCategory(this));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem updateMenuItem = new MenuItem("Update");
		updateMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/EditCategory.fxml", new EditCategory(this, tableView.getSelectionModel().getSelectedItem()));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem statusMenuItem = new MenuItem("Change status");
		statusMenuItem.setOnAction(actionEvent -> {
			Category category = tableView.getSelectionModel().getSelectedItem();
			if (api.Category.getInstance().changeStatus(category.getId(), !category.isEnabled()) != null) {
				refresh();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not change status.\nPlease notify staff.");
			}
		});

		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(actionEvent -> {
			Category category = tableView.getSelectionModel().getSelectedItem();
			if (api.Category.getInstance().delete(category.getId()) != null) {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not delete category.\nBecause some foods are belonged this category.");
			} else {
				refresh();
			}
		});

		ContextMenu contextMenuTableView = new ContextMenu(insertMenuItem, updateMenuItem, statusMenuItem, deleteMenuItem);
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
			contextMenuTableView.show(tableView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
		});
	}

	public void refresh() {
		tableView.getItems().clear();
		tableView.setItems(FXCollections.observableArrayList(api.Category.getInstance().getCategories()));
	}
}
