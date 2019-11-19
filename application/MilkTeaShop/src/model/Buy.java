package model;

import app.alert.AlertError;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Buy {
	private int id;
	private String name;
	private double income;

	public Buy(ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			income = resultSet.getDouble("income");
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getIncome() {
		return income;
	}
}
