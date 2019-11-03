package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Food implements Model{
    private int id;
    private String name;
    private int idCategory;
    private double price;
    private boolean enabled;

    public Food(@NotNull ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            idCategory = resultSet.getInt("idCategory");
            price = resultSet.getDouble("price");
            enabled = resultSet.getBoolean("status");
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

    public int getIdCategory() {
        return idCategory;
    }

    public double getPrice() {
        return price;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
