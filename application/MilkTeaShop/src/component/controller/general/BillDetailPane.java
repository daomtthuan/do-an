package component.controller.general;

import app.pattern.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.BillDetail;
import model.Discount;
import tool.Number;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BillDetailPane implements Controller, Initializable {
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
	private double sale;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		nameFoodColumn.setCellValueFactory(new PropertyValueFactory<>("nameFood"));
		nameCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("nameCategory"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
	}

	public void setBillDetails(Account account, Discount discount, ArrayList<BillDetail> billDetails) {
		billDetailTableView.getItems().clear();
		billDetailTableView.setItems(FXCollections.observableArrayList(billDetails));
		double totalBefore = 0, total = 0;
		sale = 0;
		for (BillDetail billDetail : billDetails) {
			totalBefore += billDetail.getTotal();
		}
		if (account != null) {
			sale += 2;
		}
		if (discount != null) {
			sale += discount.getSale();
		}
		total = totalBefore - (totalBefore * sale / 100.0);

		totalBeforeLabel.setText("$" + Number.round(totalBefore, 2));
		saleLabel.setText("- " + Number.round(sale, 2) + "%");
		totalLabel.setText("$" + Number.round(total, 2));
	}

	double getSale() {
		return sale;
	}
}
