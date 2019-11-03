package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableFood implements Model {
    private int id;
    private int x;
    private int y;
    private boolean enabled;

    public TableFood(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            x = data.getInt("x");
            y = data.getInt("y");
            enabled = data.getBoolean("status");
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
