package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Bill {
    private int id;
    private TableFood tableFood;
    private Account account;
    private Discount discount;
    private String checkIn;
    private String checkOut;

    @Contract(pure = true)
    public Bill() {
    }

    public Bill(int id, TableFood tableFood, Account account, Discount discount, String checkIn, String checkOut) {
        this.id = id;
        this.tableFood = new TableFood(tableFood);
        this.account = new Account(account);
        this.discount = new Discount(discount);
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Bill(@NotNull Bill bill) {
        this.id = bill.id;
        this.tableFood = new TableFood(bill.tableFood);
        this.account = new Account(bill.account);
        this.discount = new Discount(bill.discount);
        this.checkIn = bill.checkIn;
        this.checkOut = bill.checkOut;
    }

    public int getId() {
        return id;
    }

    public TableFood getTableFood() {
        return tableFood;
    }

    public Account getAccount() {
        return account;
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
}
