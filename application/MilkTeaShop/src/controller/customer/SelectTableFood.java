package controller.customer;

import app.stage.SecondaryStage;
import component.controller.TableFoodPane;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.BillDetail;
import org.jetbrains.annotations.Contract;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

class SelectTableFood implements Controller, Initializable {
	@FXML
	private VBox tableFoodComponent;
	private ArrayList<BillDetail> billDetails;

	@Contract(pure = true)
	SelectTableFood(ArrayList<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	@FXML
	private void back() {
		SecondaryStage.getInstance().setScene("/view/customer/Order.fxml", "/style/customer/Order.css", new Order(billDetails));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		TableFoodPane tableFoodPane = new  TableFoodPane() {
			@Override
			public void selectTableFood() {

			}
		};
		tableFoodComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/TableFoodPane.fxml", tableFoodPane));
	}
}
