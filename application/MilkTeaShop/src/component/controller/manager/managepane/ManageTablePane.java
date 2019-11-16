package component.controller.manager.managepane;

import app.alert.AlertWarning;
import app.pattern.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.Table;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageTablePane implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private TableView<Table> tableView;
	private TableColumn<Table, Integer> idColumn;
	private TableColumn<Table, Boolean> xColumn;
	private TableColumn<Table, Boolean> yColumn;
	private TableColumn<Table, Integer> statusColumn;

	public ManageTablePane() {
		idColumn = new TableColumn<>("Id Table");
		xColumn = new TableColumn<>("Position X");
		yColumn = new TableColumn<>("Position Y");
		statusColumn = new TableColumn<>("Enabled");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
		yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameTableView.setText("Table List");
		tableView.setPrefSize(1345, 650);
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(xColumn);
		tableView.getColumns().add(yColumn);
		tableView.getColumns().add(statusColumn);
		tableView.setItems(FXCollections.observableArrayList(api.Table.getInstance().getTables()));

		MenuItem insertMenuItem = new MenuItem("Insert");
		insertMenuItem.setOnAction(actionEvent -> {
			if (api.Table.getInstance().insert(10, 10) != null) {
				refresh();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not insert table.\nPlease notify staff.");
			}
		});

		MenuItem statusMenuItem = new MenuItem("Change status");
		statusMenuItem.setOnAction(actionEvent -> {
			Table table = tableView.getSelectionModel().getSelectedItem();
			if (api.Table.getInstance().changeStatus(table.getId(), table.getStatus() > 0 ? 0 : 1) != null) {
				refresh();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not change status.\nPlease notify staff.");
			}
		});

		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(actionEvent -> {
			Table table = tableView.getSelectionModel().getSelectedItem();
			if (api.Table.getInstance().delete(table.getId()) != null) {
				AlertWarning.getInstance().showAndWait("Fail!", "Can not delete table.\nBecause some bills are using information of this table.");
			} else {
				refresh();
			}
		});

		ContextMenu contextMenu = new ContextMenu(insertMenuItem, statusMenuItem, deleteMenuItem);
		tableView.setOnContextMenuRequested(contextMenuEvent -> {
			if (tableView.getSelectionModel().isEmpty()) {
				statusMenuItem.setDisable(true);
				deleteMenuItem.setDisable(true);
			} else {
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

	private void refresh() {
		tableView.getItems().clear();
		tableView.setItems(FXCollections.observableArrayList(api.Table.getInstance().getTables()));
	}
}
