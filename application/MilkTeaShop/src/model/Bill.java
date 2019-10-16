package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
