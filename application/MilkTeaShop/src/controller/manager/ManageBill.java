package controller.manager;

import app.pattern.Controller;
import component.controller.ManagePane;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bill;

import java.util.ArrayList;

public class ManageBill extends ManagePane implements Controller {
	@FXML
	private TableView<Bill> tableView;
	private TableColumn<Bill, Integer> idColumn;
	private TableColumn<Bill, Integer> idTableColumn;
	private TableColumn<Bill, Integer> idCustomerColumn;
	private TableColumn<Bill, Integer> idEmployeeColumn;
	private TableColumn<Bill, String> nameDiscountColumn;
	private TableColumn<Bill, Double> saleColumn;
	private TableColumn<Bill, String> checkInColumn;
	private TableColumn<Bill, String> checkOutColumn;
	private ArrayList<Bill> bills;

	ManageBill(Manager manager) {
		super(manager);
		idColumn = new TableColumn<>("Id Bill");
		idTableColumn = new TableColumn<>("Id Table");
		idCustomerColumn = new TableColumn<>("Id Customer");
		idEmployeeColumn = new TableColumn<>("Id Employee");
		nameDiscountColumn = new TableColumn<>("Discount Code");
		saleColumn = new TableColumn<>("Sale");
		checkInColumn = new TableColumn<>("Check In");
		checkOutColumn = new TableColumn<>("Check Out");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idTableColumn.setCellValueFactory(new PropertyValueFactory<>("idTable"));
		idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
		idEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
		nameDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("nameDiscount"));
		saleColumn.setCellValueFactory(new PropertyValueFactory<>("sale"));
		checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
		checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
	}

	@Override
	protected void setup() {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(idTableColumn);
		tableView.getColumns().add(idCustomerColumn);
		tableView.getColumns().add(idEmployeeColumn);
		tableView.getColumns().add(nameDiscountColumn);
		tableView.getColumns().add(saleColumn);
		tableView.getColumns().add(checkInColumn);
		tableView.getColumns().add(checkOutColumn);

		bills = api.Bill.getInstance().getBills();
	}

	@Override
	protected void load() {
		tableView.setItems(FXCollections.observableArrayList(bills));
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
