package model;

import app.alert.AlertError;
import app.pattern.Model;
import tool.Number;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bill implements Model {
	private int id;
	private int idTable;
	private int idCustomer;
	private String nameCustomer;
	private int idEmployee;
	private String nameEmployee;
	private int idDiscount;
	private String nameDiscount;
	private double sale;
	private String checkIn;
	private String checkOut;
	private double total;

	public Bill(ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
			idTable = resultSet.getInt("idTable");
			idCustomer = resultSet.getInt("idCustomer");
			nameCustomer = resultSet.getString("nameCustomer");
			idEmployee = resultSet.getInt("idEmployee");
			nameEmployee = resultSet.getString("nameEmployee");
			idDiscount = resultSet.getInt("idDiscount");
			nameDiscount = resultSet.getString("nameDiscount");
			sale = resultSet.getDouble("sale");
			checkIn = resultSet.getString("checkIn");
			checkOut = resultSet.getString("checkOut");
			total = resultSet.getDouble("total");
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

	public String getNameCustomer() {
		return nameCustomer != null ? nameCustomer : "-";
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public String getNameEmployee() {
		return nameEmployee;
	}

	public int getIdDiscount() {
		return idDiscount;
	}

	public String getNameDiscount() {
		return nameDiscount != null ? nameDiscount : "-";
	}

	public double getSale() {
		return Number.round(sale, 2);
	}

	public String getCheckIn() throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(checkIn));
	}

	public String getCheckOut() throws ParseException {
		return checkOut != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(checkOut)) : "-";
	}

	public double getTotal() {
		return Number.round(total, 2);
	}
}
