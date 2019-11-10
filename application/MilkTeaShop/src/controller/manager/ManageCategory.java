package controller.manager;

import app.pattern.Controller;
import component.controller.ManagePane;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;

import java.util.ArrayList;

public class ManageCategory extends ManagePane implements Controller {

	@FXML
	private TableView<Category> tableView;
	private TableColumn<Category, Integer> idColumn;
	private TableColumn<Category, String> nameColumn;
	private TableColumn<Category, Boolean> enabledColumn;
	private ArrayList<Category> categories;
	ManageCategory(Manager manager) {
		super(manager);

		idColumn = new TableColumn<>("Id Discount");
		nameColumn = new TableColumn<>("Name");
		enabledColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}
	@Override
	protected void setup() {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(enabledColumn);

		categories = api.Category.getInstance().getCategories();
	}

	@Override
	protected void load() {
		tableView.setItems(FXCollections.observableArrayList(categories));
	}

	@Override
	protected void insert() {

	}

	@Override
	protected void update() {

	}

	@Override
	protected void delete() {

	}

	@Override
	protected void refresh() {
		tableView.getItems().clear();
		load();
	}
}
