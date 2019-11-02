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

    public Food(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            name = data.getString("name");
            idCategory = data.getInt("idCategory");
            price = data.getDouble("price");
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
}
