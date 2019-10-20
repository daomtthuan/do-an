package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Food.
 */
public class Food {
    private int id;
    private String name;
    private int idCategory;
    private boolean status;

    /**
     * Instantiates a new Food.
     *
     * @param id         the id
     * @param name       the name
     * @param idCategory the id category
     * @param status     the status
     */
    @Contract(pure = true)
    public Food(int id, String name, int idCategory, boolean status) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.status = status;
    }

    /**
     * Instantiates a new Food.
     *
     * @param data the data
     */
    public Food(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.idCategory = data.getInt("idCategory");
            this.status = data.getBoolean("status");
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

    /**
     * Gets id category.
     *
     * @return the id category
     */
    public int getIdCategory() {
        return idCategory;
    }

    /**
     * Is status boolean.
     *
     * @return the boolean
     */
    public boolean isStatus() {
        return status;
    }
}
