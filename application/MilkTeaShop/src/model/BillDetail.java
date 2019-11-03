package model;

import app.alert.AlertError;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail implements Model {
    private int id;
    private int idBill;
    private int idFood;
    private String foodName;
    private double foodPrice;
    private int quantity;
    private double total;

    @Contract(pure = true)
    public BillDetail(int idFood, String foodName, double foodPrice, int quantity) {
        this.idFood = idFood;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
        total = foodPrice*quantity;
    }

    public BillDetail(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            idBill = data.getInt("idBill");
            idFood = data.getInt("idFood");
            quantity = data.getInt("quantity");
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
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

    public String getFoodName() {
        return foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        total = quantity * foodPrice;
    }

    public double getTotal() {
        return total;
    }
}
