package controller.manager;

import app.pattern.Controller;
import component.controller.ManagePane;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Table;

import java.util.ArrayList;

public class ManageTable extends ManagePane implements Controller {

	@FXML
	private TableView<Table> tableView;
	private TableColumn<Table, Integer> idColumn;
	private TableColumn<Table, Boolean> xColumn;
	private TableColumn<Table, Boolean> yColumn;
	private TableColumn<Table, Integer> statusColumn;
	private ArrayList<Table> tables;

	ManageTable(Manager manager) {
		super(manager);
		idColumn = new TableColumn<>("Id Table");
		xColumn = new TableColumn<>("X");
		yColumn = new TableColumn<>("Y");
		statusColumn = new TableColumn<>("Status");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
		yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

	}

	@Override
	protected void setup() {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(xColumn);
		tableView.getColumns().add(yColumn);
		tableView.getColumns().add(statusColumn);

		tables = api.Table.getInstance().getTables();
	}

	@Override
	protected void load() {
		tableView.setItems(FXCollections.observableArrayList(tables));
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
