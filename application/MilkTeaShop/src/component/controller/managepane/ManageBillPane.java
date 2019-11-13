package component.controller.managepane;

import app.pattern.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bill;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageBillPane implements Controller, Initializable {
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

	ManageBillPane() {
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
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(idTableColumn);
		tableView.getColumns().add(idCustomerColumn);
		tableView.getColumns().add(idEmployeeColumn);
		tableView.getColumns().add(nameDiscountColumn);
		tableView.getColumns().add(saleColumn);
		tableView.getColumns().add(checkInColumn);
		tableView.getColumns().add(checkOutColumn);

		tableView.setItems(FXCollections.observableArrayList(api.Bill.getInstance().getBills()));
	}
}
