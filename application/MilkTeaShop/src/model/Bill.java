package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill implements Model {
    private int id;
    private int idTableFood;
    private int idCustomer;
    private int idEmployee;
    private int idDiscount;
    private String checkIn;
    private String checkOut;

    public Bill(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            idTableFood = data.getInt("idTableFood");
            idCustomer = data.getInt("idAccount");
            idEmployee = data.getInt("idEmployee");
            idDiscount = data.getInt("idDiscount");
            checkIn = data.getString("checkIn");
            checkOut = data.getString("checkOut");
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public int getId() {
        return id;
    }

    public int getIdTableFood() {
        return idTableFood;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
}
