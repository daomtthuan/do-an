package component.controller;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BillDetail;

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
	private TableColumn<BillDetail, String> foodNameColumn;
	@FXML
	private TableColumn<BillDetail, Double> foodPriceColumn;
	@FXML
	private TableColumn<BillDetail, Integer> quantityColumn;
	@FXML
	private TableColumn<BillDetail, Double> totalColumn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
		foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
		setup();
	}

	protected void setTotalBefore(double totalBefore) {
		totalBeforeLabel.setText("$" + totalBefore);
	}

	protected void setSale(double sale) {
		saleLabel.setText("- " + sale + "%");
	}

	protected void setTotal(double total) {
		totalLabel.setText("$" + total);
	}

	public abstract void setup();

	public void setBillDetails(ArrayList<BillDetail> billDetails) {
		billDetailTableView.setItems(FXCollections.observableArrayList(billDetails));
	}
}
