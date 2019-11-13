package component.controller.managepane;

import app.pattern.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageManager implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private TableView<Account> tableView;
	private TableColumn<Account, Integer> idColumn;
	private TableColumn<Account, String> accountColumn;
	private TableColumn<Account, String> nameColumn;
	private TableColumn<Account, String> genderColumn;
	private TableColumn<Account, String> birthdayColumn;
	private TableColumn<Account, String> addressColumn;
	private TableColumn<Account, String> phoneColumn;
	private TableColumn<Account, String> emailColumn;
	private TableColumn<Account, String> statusColumn;

	ManageManager() {
		idColumn = new TableColumn<>("Id Customer");
		accountColumn = new TableColumn<>("Account");
		nameColumn = new TableColumn<>("Name");
		genderColumn = new TableColumn<>("Male");
		birthdayColumn = new TableColumn<>("Birthday");
		addressColumn = new TableColumn<>("Address");
		phoneColumn = new TableColumn<>("Phone");
		emailColumn = new TableColumn<>("Email");
		statusColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("male"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(accountColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(genderColumn);
		tableView.getColumns().add(birthdayColumn);
		tableView.getColumns().add(addressColumn);
		tableView.getColumns().add(phoneColumn);
		tableView.getColumns().add(emailColumn);
		tableView.getColumns().add(statusColumn);

		tableView.setItems(FXCollections.observableArrayList(api.Account.getInstance().getAccounts(3)));
	}
}
