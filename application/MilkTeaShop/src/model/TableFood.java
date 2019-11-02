package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableFood implements Model {
    private int id;
    private boolean status;
    private int x;
    private int y;

    public TableFood(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            status = data.getBoolean("status");
            x = data.getInt("x");
            y = data.getInt("y");
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
