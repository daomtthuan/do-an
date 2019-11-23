package tool;

import app.alert.AlertWarning;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Income;

import java.time.LocalDate;
import java.util.ArrayList;

public class Chart {
	public static AreaChart<java.lang.Number, java.lang.Number> create() {
		LocalDate now = LocalDate.now();
		AreaChart<java.lang.Number, java.lang.Number> areaChart = new AreaChart<>(new NumberAxis(1, now.getMonth().maxLength(), 1), new NumberAxis());
		areaChart.setTitle("Income in month");
		areaChart.setLegendSide(Side.TOP);

		XYChart.Series<java.lang.Number, java.lang.Number> thisMonthSeries = new XYChart.Series<>();
		thisMonthSeries.setName("This month");
		ArrayList<Income> thisMonthIncomes = api.Income.getInstance().getDayIncomes(now.getYear() + "-" + now.getMonthValue() + "-1", now.getYear() + "-" + now.getMonthValue() + "-" + now.getMonth().maxLength());
		if (thisMonthIncomes != null) {
			thisMonthIncomes.forEach(income -> thisMonthSeries.getData().add(new XYChart.Data<>(LocalDate.parse(income.getDate()).getDayOfMonth(), income.getIncome())));
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Cannot calculate income.\nPlease notify staff.");
		}
		areaChart.getData().add(thisMonthSeries);

		XYChart.Series<java.lang.Number, java.lang.Number> lastMonthSeries = new XYChart.Series<>();
		lastMonthSeries.setName("Last month");
		ArrayList<Income> lastMonthIncomes = api.Income.getInstance().getDayIncomes(now.getYear() + "-" + (now.getMonthValue() - 1) + "-1", now.getYear() + "-" + (now.getMonthValue() - 1) + "-" + now.getMonth().minus(1).maxLength());
		if (lastMonthIncomes != null) {
			lastMonthIncomes.forEach(income -> lastMonthSeries.getData().add(new XYChart.Data<>(LocalDate.parse(income.getDate()).getDayOfMonth(), income.getIncome())));
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Cannot calculate income.\nPlease notify staff.");
		}
		areaChart.getData().add(lastMonthSeries);
		return areaChart;
	}
}
