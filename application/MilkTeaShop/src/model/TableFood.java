package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

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
     * @param id     the id
     * @param status the status
     * @param x      the x
     * @param y      the y
     */
    @Contract(pure = true)
    public TableFood(int id, boolean status, int x, int y) {
        this.id = id;
        this.status = status;
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Table food.
     *
     * @param data the data
     */
    public TableFood(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.status = data.getBoolean("status");
            this.x = data.getInt("x");
            this.y = data.getInt("y");
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
