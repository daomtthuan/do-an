package controller.manager;

import app.pattern.Controller;
import component.controller.ManagePane;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Discount;

import java.util.ArrayList;

public class ManageDiscount extends ManagePane implements Controller {
	@FXML
	private TableView<Discount> tableView;
	private TableColumn<Discount, Integer> idColumn;
	private TableColumn<Discount, String> nameColumn;
	private TableColumn<Discount, Double> saleColumn;
	private TableColumn<Discount, Boolean> enabledColumn;
	private ArrayList<Discount> discounts;

	ManageDiscount(Manager manager) {
		super(manager);

		idColumn = new TableColumn<>("Id Discount");
		nameColumn = new TableColumn<>("Code");
		saleColumn = new TableColumn<>("Sale");
		enabledColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		saleColumn.setCellValueFactory(new PropertyValueFactory<>("sale"));
		enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}

	@Override
	protected void setup() {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(saleColumn);
		tableView.getColumns().add(enabledColumn);

		discounts = api.Discount.getInstance().getDiscounts();
	}

	@Override
	protected void load() {
		tableView.setItems(FXCollections.observableArrayList(discounts));
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
