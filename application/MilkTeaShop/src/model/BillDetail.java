package model;

import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Bill detail.
 */
public class BillDetail {
    private int id;
    private int idBill;
    private int idFood;
    private int quantity;

    /**
     * Instantiates a new Bill detail.
     *
     * @param data the data
     */
    public BillDetail(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            idBill = data.getInt("idBill");
            idFood = data.getInt("idFood");
            quantity = data.getInt("quantity");
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
     * Gets id bill.
     *
     * @return the id bill
     */
    public int getIdBill() {
        return idBill;
    }

    /**
     * Gets id food.
     *
     * @return the id food
     */
    public int getIdFood() {
        return idFood;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }
}
