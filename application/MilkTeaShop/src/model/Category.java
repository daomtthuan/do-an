package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category implements Model{
    private int id;
    private String name;
    private boolean enabled;

    public Category(@NotNull ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
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

    public boolean isEnabled() {
        return enabled;
    }
}
