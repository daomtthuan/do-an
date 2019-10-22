package model;

import library.ErrorAlert;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Bill.
 */
public class Bill {
    private int id;
    private int idTableFood;
    private int idAccount;
    private int idDiscount;
    private String checkIn;
    private String checkOut;

    /**
     * Instantiates a new Bill.
     *
     * @param data the data
     */
    public Bill(@NotNull ResultSet data) {
        try {
            id = data.getInt("id");
            idTableFood = data.getInt("idTableFood");
            idAccount = data.getInt("idAccount");
            idDiscount = data.getInt("idDiscount");
            checkIn = data.getString("checkIn");
            checkOut = data.getString("checkOut");
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
     * Gets id table food.
     *
     * @return the id table food
     */
    public int getIdTableFood() {
        return idTableFood;
    }

    /**
     * Gets id account.
     *
     * @return the id account
     */
    public int getIdAccount() {
        return idAccount;
    }

    /**
     * Gets id discount.
     *
     * @return the id discount
     */
    public int getIdDiscount() {
        return idDiscount;
    }

    /**
     * Gets check in.
     *
     * @return the check in
     */
    public String getCheckIn() {
        return checkIn;
    }

    /**
     * Gets check out.
     *
     * @return the check out
     */
    public String getCheckOut() {
        return checkOut;
    }
}
