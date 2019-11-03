package model;

import app.alert.AlertError;
import org.jetbrains.annotations.Contract;
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
    private double total;

    @Contract(pure = true)
    public Bill(int idCustomer, int idEmployee, int idDiscount, double total) {
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.idDiscount = idDiscount;
        this.total = total;
    }

    public Bill(@NotNull ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            idTableFood = resultSet.getInt("idTableFood");
            idCustomer = resultSet.getInt("idAccount");
            idEmployee = resultSet.getInt("idEmployee");
            idDiscount = resultSet.getInt("idDiscount");
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

    public double getTotal() {
        return total;
    }
}
