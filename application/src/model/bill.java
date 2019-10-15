package model;

import java.util.Date;

public class bill {
    private int id;
    private int table_food;
    private int account;
    private int discount;
    private Date checkin;
    private Date checkout;

    public bill() {
    }

    public bill(int id, int table_food, int account, int discount, Date checkin, Date checkout) {
        this.id = id;
        this.table_food = table_food;
        this.account = account;
        this.discount = discount;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable_food() {
        return table_food;
    }

    public void setTable_food(int table_food) {
        this.table_food = table_food;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
}
