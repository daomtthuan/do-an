package component.controller.manager.managepane;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import controller.manager.EditAccount;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.Account;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageManagerPane implements Controller, Initializable {
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

	public ManageManagerPane() {
		idColumn = new TableColumn<>("Id Manager");
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
		nameTableView.setText("Manager List");
		tableView.setPrefSize(1350, 650);
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

		MenuItem insertMenuItem = new MenuItem("Insert");
		insertMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/EditAccount.fxml", "/style/general/Account.css", new EditAccount(this::refresh, 3));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem updateMenuItem = new MenuItem("Update");
		updateMenuItem.setOnAction(actionEvent -> {
			PrimaryDialog.getInstance().setScene("/view/manager/EditAccount.fxml", "/style/general/Account.css", new EditAccount(this::refresh, tableView.getSelectionModel().getSelectedItem()));
			PrimaryDialog.getInstance().getStage().show();
			PrimaryStage.getInstance().getStage().hide();
		});

		MenuItem statusMenuItem = new MenuItem("Change status");
		statusMenuItem.setOnAction(actionEvent -> {
			Account account = tableView.getSelectionModel().getSelectedItem();
			if (api.Account.getInstance().changeStatus(account.getId(), !account.isEnabled()) != null) {
				refresh();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not change status.\nPlease notify staff.");
			}
		});

		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(actionEvent -> {
			Account account = tableView.getSelectionModel().getSelectedItem();
			if (api.Account.getInstance().delete(account.getId()) != null) {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not delete account.\nBecause some bills are using information of this account.");
			} else {
				refresh();
			}
		});

		ContextMenu contextMenu = new ContextMenu(insertMenuItem, updateMenuItem, statusMenuItem, deleteMenuItem);
		tableView.setOnContextMenuRequested(contextMenuEvent -> {
			if (tableView.getSelectionModel().isEmpty()) {
				updateMenuItem.setDisable(true);
				statusMenuItem.setDisable(true);
				deleteMenuItem.setDisable(true);
			} else {
				updateMenuItem.setDisable(false);
				statusMenuItem.setDisable(false);
				deleteMenuItem.setDisable(false);
			}
			contextMenu.show(tableView, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
		});
		tableView.setOnMouseClicked(mouseEvent -> {
			if (contextMenu.isShowing() && mouseEvent.getButton() != MouseButton.SECONDARY) {
				contextMenu.hide();
			}
		});
	}

	public void refresh() {
		tableView.getItems().clear();
		tableView.setItems(FXCollections.observableArrayList(api.Account.getInstance().getAccounts(3)));
	}
}
