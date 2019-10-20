package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

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
     * @param id       the id
     * @param idBill   the id bill
     * @param idFood   the id food
     * @param quantity the quantity
     */
    @Contract(pure = true)
    public BillDetail(int id, int idBill, int idFood, int quantity) {
        this.id = id;
        this.idBill = idBill;
        this.idFood = idFood;
        this.quantity = quantity;
    }

    /**
     * Instantiates a new Bill detail.
     *
     * @param data the data
     */
    public BillDetail(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.idBill = data.getInt("idBill");
            this.idFood = data.getInt("idFood");
            this.quantity = data.getInt("quantity");
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
