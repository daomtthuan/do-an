package model;

import app.alert.AlertError;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Income {
	private String date;
	private double income;

	public Income(ResultSet resultSet) {
		try {
			date = resultSet.getString("date");
			income = resultSet.getDouble("income");
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public String getDate() {
		return date;
	}

	public double getIncome() {
		return income;
	}
}
