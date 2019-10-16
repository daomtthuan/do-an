package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill {
    private int id;
    private int idTableFood;
    private int idAccount;
    private int idDiscount;
    private String checkIn;
    private String checkOut;

    @Contract(pure = true)
    public Bill(int id, int idTableFood, int idAccount, int idDiscount, String checkIn, String checkOut) {
        this.id = id;
        this.idTableFood = idTableFood;
        this.idAccount = idAccount;
        this.idDiscount = idDiscount;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Bill(ResultSet data) throws SQLException {
        this.id = data.getInt("id");
        this.idTableFood = data.getInt("idTableFood");
        this.idAccount = data.getInt("idAccount");
        this.idDiscount = data.getInt("idDiscount");
        this.checkIn = data.getString("checkIn");
        this.checkOut = data.getString("checkOut");
    }

    public int getId() {
        return id;
    }

    public int getIdTableFood() {
        return idTableFood;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
}
