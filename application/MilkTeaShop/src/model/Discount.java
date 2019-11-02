package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount implements Model{
    private int id;
    private String name;
    private double sale;

    public Discount(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            name = data.getString("name");
            sale = data.getDouble("sale");
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
}
