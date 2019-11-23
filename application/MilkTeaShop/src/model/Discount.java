package model;

import app.alert.AlertError;
import app.pattern.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount implements Model {
	private int id;
	private String name;
	private double sale;
	private int status;

	public Discount(ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			sale = resultSet.getDouble("sale");
			status = resultSet.getInt("status");
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

	public double getSale() {
		return sale;
	}

	public boolean isEnabled() {
		return status > 0;
	}

	public boolean isGiveOut() {
		return status == 2;
	}
}
