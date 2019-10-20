package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Roll.
 */
public class Roll {
    private int id;
    private String name;

    /**
     * Instantiates a new Roll.
     *
     * @param id   the id
     * @param name the name
     */
    @Contract(pure = true)
    public Roll(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new Roll.
     *
     * @param data the data
     */
    public Roll(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
