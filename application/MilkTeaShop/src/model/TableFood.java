package model;

import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Table food.
 */
public class TableFood {
    private int id;
    private boolean status;
    private int x;
    private int y;

    /**
     * Instantiates a new Table food.
     *
     * @param data the data
     */
    public TableFood(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            status = data.getBoolean("status");
            x = data.getInt("x");
            y = data.getInt("y");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Is status boolean.
     *
     * @return the boolean
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }
}
