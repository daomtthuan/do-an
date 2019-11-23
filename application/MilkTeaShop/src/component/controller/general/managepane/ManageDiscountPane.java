package component.controller.general.managepane;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import controller.manager.EditDiscount;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.Discount;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageDiscountPane implements Controller, Initializable {
	@FXML
	private Label nameTableView;
	@FXML
	private TableView<Discount> tableView;
	private TableColumn<Discount, Integer> idColumn;
	private TableColumn<Discount, String> nameColumn;
	private TableColumn<Discount, Double> saleColumn;
	private TableColumn<Discount, Boolean> enabledColumn;
	private TableColumn<Discount, Boolean> giveOutColumn;

	public ManageDiscountPane() {
		idColumn = new TableColumn<>("Id Discount");
		nameColumn = new TableColumn<>("Code");
		saleColumn = new TableColumn<>("Sale");
		enabledColumn = new TableColumn<>("Enabled");
		giveOutColumn = new TableColumn<>("Give out");

		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		saleColumn.setCellValueFactory(new PropertyValueFactory<>("sale"));
		enabledColumn.setCellValueFactory(new PropertyValueFactory<>("enabled"));
		giveOutColumn.setCellValueFactory(new PropertyValueFactory<>("giveOut"));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameTableView.setText("Discount Code List");
		tableView.setPrefSize(1350, 650);
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(saleColumn);
		tableView.getColumns().add(enabledColumn);
		tableView.getColumns().add(giveOutColumn);
		tableView.setItems(FXCollections.observableArrayList(api.Discount.getInstance().getDiscounts()));

		if (PrimaryStage.getInstance().getAccount().getRoll() == 3) {
			MenuItem insertMenuItem = new MenuItem("Insert");
			insertMenuItem.setOnAction(actionEvent -> {
				PrimaryDialog.getInstance().setScene("/view/manager/EditDiscount.fxml", new EditDiscount(this::refresh));
				PrimaryDialog.getInstance().getStage().show();
				PrimaryStage.getInstance().getStage().hide();
			});

			MenuItem giveOutMenuItem = new MenuItem("Change giving out");
			giveOutMenuItem.setOnAction(actionEvent -> {
				Discount discount = tableView.getSelectionModel().getSelectedItem();
				if (api.Discount.getInstance().changeStatus(discount.getId(), discount.isGiveOut() ? 1 : 2) != null) {
					refresh();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot change giving out.\nPlease notify staff.");
				}
			});

			MenuItem updateMenuItem = new MenuItem("Update");
			updateMenuItem.setOnAction(actionEvent -> {
				PrimaryDialog.getInstance().setScene("/view/manager/EditDiscount.fxml", new EditDiscount(this::refresh, tableView.getSelectionModel().getSelectedItem()));
				PrimaryDialog.getInstance().getStage().show();
				PrimaryStage.getInstance().getStage().hide();
			});

			MenuItem statusMenuItem = new MenuItem("Change status");
			statusMenuItem.setOnAction(actionEvent -> {
				Discount discount = tableView.getSelectionModel().getSelectedItem();
				if (api.Discount.getInstance().changeStatus(discount.getId(), discount.isEnabled() ? 0 : 1) != null) {
					refresh();
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot change status.\nPlease notify staff.");
				}
			});

			MenuItem deleteMenuItem = new MenuItem("Delete");
			deleteMenuItem.setOnAction(actionEvent -> {
				Discount category = tableView.getSelectionModel().getSelectedItem();
				if (api.Discount.getInstance().delete(category.getId()) != null) {
					AlertWarning.getInstance().showAndWait("Fail!", "Cannot delete discount code.\nBecause some bills used this code.");
				} else {
					refresh();
				}
			});

			ContextMenu contextMenu = new ContextMenu(insertMenuItem, giveOutMenuItem, updateMenuItem, statusMenuItem, deleteMenuItem);
			tableView.setOnContextMenuRequested(contextMenuEvent -> {
				if (tableView.getSelectionModel().isEmpty()) {
					giveOutMenuItem.setDisable(true);
					updateMenuItem.setDisable(true);
					statusMenuItem.setDisable(true);
					deleteMenuItem.setDisable(true);
				} else {
					giveOutMenuItem.setDisable(false);
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
	}

	public void refresh() {
		tableView.getItems().clear();
		tableView.setItems(FXCollections.observableArrayList(api.Discount.getInstance().getDiscounts()));
	}
}
