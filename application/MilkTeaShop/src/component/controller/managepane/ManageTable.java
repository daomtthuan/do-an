package component.controller.managepane;

import app.pattern.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Table;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageTable implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private TableView<Table> tableView;
	private TableColumn<Table, Integer> idColumn;
	private TableColumn<Table, Boolean> xColumn;
	private TableColumn<Table, Boolean> yColumn;
	private TableColumn<Table, Integer> statusColumn;

	ManageTable() {
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
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(xColumn);
		tableView.getColumns().add(yColumn);
		tableView.getColumns().add(statusColumn);
		tableView.setItems(FXCollections.observableArrayList(api.Table.getInstance().getTables()));
	}
}
