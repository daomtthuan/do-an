package component.controller.manager;

import app.pattern.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Buy;
import model.Sell;
import tool.Chart;
import tool.Number;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageIncomePane implements Controller, Initializable {
	@FXML
	private VBox manageIncomePane;
	@FXML
	private VBox chartPane;
	@FXML
	private Label idCustomerLabel;
	@FXML
	private Label nameCustomerLabel;
	@FXML
	private Label totalBuyLabel;
	@FXML
	private Label nameFoodLabel;
	@FXML
	private Label totalQuantityLabel;
	@FXML
	private Label totalSellLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Buy topBuy = api.Income.getInstance().getTopBuy();
		Sell topSell = api.Income.getInstance().getTopSell();
		idCustomerLabel.setText("Id Customer:\t\t" + (topBuy != null ? topBuy.getId() : "-"));
		nameCustomerLabel.setText("Name Customer:\t" + (topBuy != null ? topBuy.getName() : "-"));
		totalBuyLabel.setText("Total buy:\t\t$" + (topBuy != null ? Number.round(topBuy.getIncome(), 2) : "-"));
		nameFoodLabel.setText("Food - Category:\t" + (topSell != null ? topSell.getNameFood() + " - " + topSell.getNameCategory() : "-"));
		totalQuantityLabel.setText("Total quantity:\t\t" + (topSell != null ? topSell.getQuantity() : "-"));
		totalSellLabel.setText("Total sell:\t\t\t$" + (topSell != null ? Number.round(topSell.getIncome(), 2) : "-"));
		AreaChart chart = Chart.create();
		chart.setPrefHeight(545);
		chartPane.getChildren().add(chart);
	}
}
