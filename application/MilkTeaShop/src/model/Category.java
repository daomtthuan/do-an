package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type AccessCategory.
 */
public class Category {

    private int id;
    private String name;

    /**
     * Instantiates a new AccessCategory.
     *
     * @param id   the id
     * @param name the name
     */
    @Contract(pure = true)
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new AccessCategory.
     *
     * @param data the data
     */
    public Category(@NotNull ResultSet data) {
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
