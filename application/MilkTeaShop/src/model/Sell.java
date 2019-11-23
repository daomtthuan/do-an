package model;

import app.alert.AlertError;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sell {
	private int idFood;
	private String nameFood;
	private int idCategory;
	private String nameCategory;
	private int quantity;
	private double income;

	public Sell(ResultSet resultSet) {
		try {
			idFood = resultSet.getInt("idFood");
			nameFood = resultSet.getString("nameFood");
			idCategory = resultSet.getInt("idCategory");
			nameCategory = resultSet.getString("nameCategory");
			quantity = resultSet.getInt("quantity");
			income = resultSet.getDouble("income");
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public int getIdFood() {
		return idFood;
	}

	public String getNameFood() {
		return nameFood;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getIncome() {
		return income;
	}
}
