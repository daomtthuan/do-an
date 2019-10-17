package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail {
    private int id;
    private int idBill;
    private int idFood;
    private int quantity;

    @Contract(pure = true)
    public BillDetail(int id, int idBill, int idFood, int quantity) {
        this.id = id;
        this.idBill = idBill;
        this.idFood = idFood;
        this.quantity = quantity;
    }

    public BillDetail(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.idBill = data.getInt("idBill");
            this.idFood = data.getInt("idFood");
            this.quantity = data.getInt("quantity");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
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
