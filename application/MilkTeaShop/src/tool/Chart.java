package tool;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Chart extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		final NumberAxis xAxis = new NumberAxis(1, 12, 1);
		final NumberAxis yAxis = new NumberAxis();
		final AreaChart<java.lang.Number, java.lang.Number> areaChart = new AreaChart<>(xAxis, yAxis);
		areaChart.setTitle("Revenue");

		areaChart.setLegendSide(Side.LEFT);

		// Chuỗi dữ liệu của năm 2014
		XYChart.Series<java.lang.Number, java.lang.Number> series2014 = new XYChart.Series<>();

		series2014.setName("2014");

		series2014.getData().add(new XYChart.Data<>(1, 400));
		series2014.getData().add(new XYChart.Data<>(3, 1000));
		series2014.getData().add(new XYChart.Data<>(4, 1500));
		series2014.getData().add(new XYChart.Data<>(5, 800));
		series2014.getData().add(new XYChart.Data<>(7, 500));
		series2014.getData().add(new XYChart.Data<>(8, 1800));
		series2014.getData().add(new XYChart.Data<>(10, 1500));
		series2014.getData().add(new XYChart.Data<>(12, 1300));

		// Chuỗi dữ liệu của năm 2015
		XYChart.Series<java.lang.Number, java.lang.Number> series2015 = new XYChart.Series<>();
		series2015.setName("2015");
		series2015.getData().add(new XYChart.Data<>(1, 2000));
		series2015.getData().add(new XYChart.Data<>(3, 1500));
		series2015.getData().add(new XYChart.Data<>(4, 1300));
		series2015.getData().add(new XYChart.Data<>(5, 1200));
		series2015.getData().add(new XYChart.Data<>(7, 1400));
		series2015.getData().add(new XYChart.Data<>(8, 1080));
		series2015.getData().add(new XYChart.Data<>(10, 2050));
		series2015.getData().add(new XYChart.Data<>(12, 2005));

		stage.setTitle("AreaChart (o7planning.org)");
		Scene scene = new Scene(areaChart, 400, 300);
		areaChart.getData().add(series2014);
		areaChart.getData().add(series2015);
		stage.setScene(scene);
		stage.show();
	}
}
