package component.controller;

import app.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BillDetail;
import tool.Number;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class BillDetailPane implements Controller, Initializable {
	@FXML
	private Label totalBeforeLabel;
	@FXML
	private Label saleLabel;
	@FXML
	private Label totalLabel;
	@FXML
	private TableView<BillDetail> billDetailTableView;
	@FXML
	private TableColumn<BillDetail, String> nameFoodColumn;
	@FXML
	private TableColumn<BillDetail, String> nameCategoryColumn;
	@FXML
	private TableColumn<BillDetail, Integer> quantityColumn;
	@FXML
	private TableColumn<BillDetail, Double> priceColumn;
	@FXML
	private TableColumn<BillDetail, Double> totalColumn;

	public abstract void setup();

	public void refresh() {
		billDetailTableView.getItems().clear();
		setup();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameFoodColumn.setCellValueFactory(new PropertyValueFactory<>("nameFood"));
		nameCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("nameCategory"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
		setup();
	}

	protected void setBillDetails(ArrayList<BillDetail> billDetails) {
		billDetailTableView.setItems(FXCollections.observableArrayList(billDetails));
	}

	protected void setTotalBefore(double totalBefore) {
		totalBeforeLabel.setText("$" + Number.round(totalBefore, 2));
	}

	protected void setSale(double sale) {
		saleLabel.setText("- " + Number.round(sale, 2) + "%");
	}

	protected void setTotal(double total) {
		totalLabel.setText("$" + Number.round(total, 2));
	}
}
