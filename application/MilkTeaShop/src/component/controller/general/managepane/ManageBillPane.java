package component.controller.general.managepane;

import api.Account;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import controller.general.WatchAccount;
import controller.manager.ManageBillDetail;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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

		MenuItem billDetailMenuItem = new MenuItem("Bill details");
		billDetailMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/ManageBillDetail.fxml", "style/manager/ManageBillDetail.css", new ManageBillDetail(tableView.getSelectionModel().getSelectedItem()));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem watchCustomerMenuItem = new MenuItem("Customer Account");
		watchCustomerMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/general/WatchAccount.fxml", "/style/general/Account.css", new WatchAccount(Account.getInstance().getAccount(tableView.getSelectionModel().getSelectedItem().getIdCustomer())));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem watchEmployeeMenuItem = new MenuItem("Employee Account");
		watchEmployeeMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/general/WatchAccount.fxml", "/style/general/Account.css", new WatchAccount(Account.getInstance().getAccount(tableView.getSelectionModel().getSelectedItem().getIdEmployee())));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		ContextMenu contextMenu = new ContextMenu(billDetailMenuItem, watchCustomerMenuItem, watchEmployeeMenuItem);
		tableView.setOnContextMenuRequested(contextMenuEvent -> {
			if (tableView.getSelectionModel().isEmpty()) {
				billDetailMenuItem.setDisable(true);
				watchCustomerMenuItem.setDisable(true);
				watchEmployeeMenuItem.setDisable(true);
			} else {
				billDetailMenuItem.setDisable(false);
				watchCustomerMenuItem.setDisable(tableView.getSelectionModel().getSelectedItem().getIdCustomer() <= 0);
				watchEmployeeMenuItem.setDisable(false);
			}
			contextMenu.show(tableView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
		});
		tableView.setOnMouseClicked(mouseEvent -> {
			if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
				contextMenu.hide();
			}
		});
	}
}
