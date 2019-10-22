package model;

import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Category.
 */
public class Category {
    private int id;
    private String name;

    /**
     * Instantiates a new Category.
     *
     * @param data the data
     */
    public Category(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            name = data.getString("name");
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
