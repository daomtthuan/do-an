package controller.manager;

import app.pattern.Controller;
import component.controller.ManagePane;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Food;

import java.util.ArrayList;

public class ManageFood extends ManagePane implements Controller {

	@FXML
	private TableView<Food> tableView;
	private TableColumn<Food, Integer> idColumn;
	private TableColumn<Food, String> nameColumn;
	private TableColumn<Food, Integer> idCategoryColumn;
	private TableColumn<Food, Double> priceColumn;
	private TableColumn<Food, Boolean> enabledColumn;
	private ArrayList<Food> foods;
	ManageFood(Manager manager) {
		super(manager);

		idColumn = new TableColumn<>("Id Discount");
		nameColumn = new TableColumn<>("Name");
		idCategoryColumn = new TableColumn<>("Id Category");
		priceColumn = new TableColumn<>("Price");
		enabledColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("idCategory"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}

	@Override
	protected void setup() {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(idCategoryColumn);
		tableView.getColumns().add(priceColumn);
		tableView.getColumns().add(enabledColumn);

		foods = api.Food.getInstance().getFoods();
	}

	@Override
	protected void load() {
		tableView.setItems(FXCollections.observableArrayList(foods));
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
