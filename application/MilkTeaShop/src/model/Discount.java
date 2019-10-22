package model;

import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Discount.
 */
public class Discount {
    private int id;
    private String name;
    private double sale;

    /**
     * Instantiates a new Discount.
     *
     * @param data the data
     */
    public Discount(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            name = data.getString("name");
            sale = data.getDouble("sale");
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
     * Gets sale.
     *
     * @return the sale
     */
    public double getSale() {
        return sale;
    }
}
