package model;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Food.
 */
public class Food {
    private int id;
    private String name;
    private int idCategory;

    /**
     * Instantiates a new Food.
     *
     * @param data the data
     */
    public Food(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            name = data.getString("name");
            idCategory = data.getInt("idCategory");
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
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

    /**
     * Gets id category.
     *
     * @return the id category
     */
    public int getIdCategory() {
        return idCategory;
    }
}
