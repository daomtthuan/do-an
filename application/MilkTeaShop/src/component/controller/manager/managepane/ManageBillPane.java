package component.controller.manager.managepane;

import app.pattern.Controller;
import app.primary.PrimaryDialog;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bill;
import model.Table;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManageBillPane implements Controller, Initializable {
	@FXML
	private ComboBox<Table> tableComboBox;
	@FXML
	private DatePicker fromDatePicker;
	@FXML
	private DatePicker toDatePicker;
	@FXML
	private TableView<Bill> tableView;
	private TableColumn<Bill, Integer> idColumn;
	private TableColumn<Bill, String> checkInColumn;
	private TableColumn<Bill, String> checkOutColumn;
	private TableColumn<Bill, Integer> customerColumn;
	private TableColumn<Bill, Integer> employeeColumn;
	private TableColumn<Bill, String> discountColumn;
	private TableColumn<Bill, Double> saleColumn;
	private TableColumn<Bill, Double> totalColumn;

	public ManageBillPane() {
		idColumn = new TableColumn<>("Id Bill");
		checkInColumn = new TableColumn<>("Check In");
		checkOutColumn = new TableColumn<>("Check Out");
		customerColumn = new TableColumn<>("Customer");
		employeeColumn = new TableColumn<>("Employee");
		discountColumn = new TableColumn<>("Discount Code");
		saleColumn = new TableColumn<>("Sale");
		totalColumn = new TableColumn<>("Total");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
		checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
		customerColumn.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
		employeeColumn.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
		discountColumn.setCellValueFactory(new PropertyValueFactory<>("nameDiscount"));
		saleColumn.setCellValueFactory(new PropertyValueFactory<>("sale"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableView.setPrefSize(1350, 610);
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(checkInColumn);
		tableView.getColumns().add(checkOutColumn);
		tableView.getColumns().add(customerColumn);
		tableView.getColumns().add(employeeColumn);
		tableView.getColumns().add(discountColumn);
		tableView.getColumns().add(saleColumn);
		tableView.getColumns().add(totalColumn);

		tableComboBox.setItems(FXCollections.observableArrayList(api.Table.getInstance().getTables()));
		tableComboBox.setOnAction(actionEvent -> loadBillList());
		fromDatePicker.setOnAction(actionEvent -> loadBillList());
		toDatePicker.setOnAction(actionEvent -> loadBillList());
		tableComboBox.getSelectionModel().select(0);
		fromDatePicker.setValue(LocalDate.now());
		toDatePicker.setValue(LocalDate.now());
		loadBillList();
	}

	private void loadBillList() {
		int id = tableComboBox.getSelectionModel().getSelectedItem().getId();
		String from = fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String to = toDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		tableView.setItems(FXCollections.observableArrayList(api.Bill.getInstance().getBills(id, from, to)));

//		MenuItem billDetailMenuItem = new MenuItem("Bill details");
//		billDetailMenuItem.setOnAction(actionEvent -> {
//			PrimaryDialog.getInstance().setScene("/view/", new EditFood(this, categories));
//			PrimaryDialog.getInstance().getStage().show();
//			PrimaryStage.getInstance().getStage().hide();
//		});
//
//		ContextMenu categoryContextMenu = new ContextMenu(insertCategoryMenuItem);
	}
}
