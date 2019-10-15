package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class BillDetail {
    private int id;
    private Bill bill;
    private Food food;
    private int quantity;

    @Contract(pure = true)
    public BillDetail() {
    }

    public BillDetail(int id, Bill bill, Food food, int quantity) {
        this.id = id;
        this.bill = new Bill(bill);
        this.food = new Food(food);
        this.quantity = quantity;
    }

    public BillDetail(@NotNull BillDetail billDetail) {
        this.id = billDetail.id;
        this.bill = new Bill(billDetail.bill);
        this.food = new Food(billDetail.food);
        this.quantity = billDetail.quantity;
    }

    public int getId() {
        return id;
    }

    public Bill getBill() {
        return bill;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }
}
