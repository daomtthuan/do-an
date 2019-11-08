package model;

import app.Model;
import app.alert.AlertError;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import tool.Number;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail implements Model {
	private int id;
	private int idBill;
	private int idFood;
	private String nameFood;
	private int idCategory;
	private String nameCategory;
	private int quantity;
	private double price;
	private double total;

	public BillDetail(@NotNull ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
			idBill = resultSet.getInt("idBill");
			idFood = resultSet.getInt("idFood");
			nameFood = resultSet.getString("nameFood");
			idCategory = resultSet.getInt("idCategory");
			nameCategory = resultSet.getString("nameCategory");
			quantity = resultSet.getInt("quantity");
			price = resultSet.getDouble("price");
			total = Number.round(quantity * price,2);
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	@Contract(pure = true)
	public BillDetail(@NotNull Category category, @NotNull Food food) {
		idFood = food.getId();
		nameFood = food.getName();
		idCategory = category.getId();
		nameCategory = category.getName();
		quantity = 1;
		price = food.getPrice();
		total = Number.round(quantity * price,2);
	}

	public int getId() {
		return id;
	}

	public int getIdBill() {
		return idBill;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		total = Number.round(quantity * price,2);
	}

	public double getPrice() {
		return price;
	}

	public double getTotal() {
		return total;
	}
}
