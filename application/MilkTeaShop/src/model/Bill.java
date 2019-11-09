package model;

import app.pattern.Model;
import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill implements Model {
	private int id;
	private int idTable;
	private int idCustomer;
	private int idEmployee;
	private String nameDiscount;
	private double sale;
	private String checkIn;
	private String checkOut;

	public Bill(@NotNull ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
			idTable = resultSet.getInt("idTable");
			idCustomer = resultSet.getInt("idCustomer");
			idEmployee = resultSet.getInt("idEmployee");
			nameDiscount = resultSet.getString("nameDiscount");
			sale = resultSet.getDouble("sale");
			checkIn = resultSet.getString("checkIn");
			checkOut = resultSet.getString("checkOut");
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public int getId() {
		return id;
	}

	public int getIdTable() {
		return idTable;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public String getNameDiscount() {
		return nameDiscount;
	}

	public double getSale() {
		return sale;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}
}
