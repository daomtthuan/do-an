package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail implements Model{
    private int id;
    private int idBill;
    private int idFood;
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }
}
