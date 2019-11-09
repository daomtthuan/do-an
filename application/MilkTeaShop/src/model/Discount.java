package model;

import app.pattern.Model;
import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount implements Model {
    private int id;
    private String name;
    private double sale;
    private boolean enabled;

    public Discount(@NotNull ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            sale = resultSet.getDouble("sale");
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

    public double getSale() {
        return sale;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
