package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

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
     * @param id   the id
     * @param name the name
     * @param sale the sale
     */
    @Contract(pure = true)
    public Discount(int id, String name, double sale) {
        this.id = id;
        this.name = name;
        this.sale = sale;
    }

    /**
     * Instantiates a new Discount.
     *
     * @param data the data
     */
    public Discount(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.sale = data.getDouble("sale");
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
