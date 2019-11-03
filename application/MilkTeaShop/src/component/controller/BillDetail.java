package component.controller;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BillDetail implements Controller, Initializable {
    @FXML
    private Label totalBeforeLabel;
    @FXML
    private Label saleLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private TableView<model.BillDetail> billDetailTableView;
    @FXML
    private TableColumn<model.BillDetail, String> foodNameColumn;
    @FXML
    private TableColumn<model.BillDetail, Double> foodPriceColumn;
    @FXML
    private TableColumn<model.BillDetail, Integer> quantityColumn;
    @FXML
    private TableColumn<model.BillDetail, Double> totalColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    public TableView<model.BillDetail> getBillDetailTableView() {
        return billDetailTableView;
    }
}
