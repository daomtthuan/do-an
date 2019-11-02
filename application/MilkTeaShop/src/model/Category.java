package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category implements Model{
    private int id;
    private String name;

    public Category(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            name = data.getString("name");
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
}
