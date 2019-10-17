package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {
    private int id;
    private String name;
    private double sale;

    @Contract(pure = true)
    public Discount(int id, String name, double sale) {
        this.id = id;
        this.name = name;
        this.sale = sale;
    }

    public Discount(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.sale = data.getDouble("sale");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
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
}
