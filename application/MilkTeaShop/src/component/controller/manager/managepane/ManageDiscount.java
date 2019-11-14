package component.controller.manager.managepane;

import app.pattern.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Discount;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageDiscount implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private TableView<Discount> tableView;
	private TableColumn<Discount, Integer> idColumn;
	private TableColumn<Discount, String> nameColumn;
	private TableColumn<Discount, Double> saleColumn;
	private TableColumn<Discount, Boolean> enabledColumn;

	ManageDiscount() {
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
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(saleColumn);
		tableView.getColumns().add(enabledColumn);

		tableView.setItems(FXCollections.observableArrayList(api.Discount.getInstance().getDiscounts()));
	}
}
