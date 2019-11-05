package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table implements Model {
	private int id;
	private double x;
	private double y;
	private int status;

	public Table(@NotNull ResultSet data) {
		try {
			id = data.getInt("id");
			x = data.getDouble("x");
			y = data.getDouble("y");
			status = data.getInt("status");
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public int getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getStatus() {
		return status;
	}

    @Override
    public String toString() {
        return id + " - Table " + id;
    }
}
