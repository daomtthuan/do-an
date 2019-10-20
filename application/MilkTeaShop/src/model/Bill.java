package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

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
     * @param id          the id
     * @param idTableFood the id table food
     * @param idAccount   the id account
     * @param idDiscount  the id discount
     * @param checkIn     the check in
     * @param checkOut    the check out
     */
    @Contract(pure = true)
    public Bill(int id, int idTableFood, int idAccount, int idDiscount, String checkIn, String checkOut) {
        this.id = id;
        this.idTableFood = idTableFood;
        this.idAccount = idAccount;
        this.idDiscount = idDiscount;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Instantiates a new Bill.
     *
     * @param data the data
     */
    public Bill(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.idTableFood = data.getInt("idTableFood");
            this.idAccount = data.getInt("idAccount");
            this.idDiscount = data.getInt("idDiscount");
            this.checkIn = data.getString("checkIn");
            this.checkOut = data.getString("checkOut");
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
