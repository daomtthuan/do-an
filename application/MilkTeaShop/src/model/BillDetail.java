package model;

import org.jetbrains.annotations.Contract;

public class BillDetail {
    private int id;
    private int idBill;
    private int idFood;
    private int quantity;

    @Contract(pure = true)
    public BillDetail(int id, int idBill, int idFood, int quantity) {
        this.id = id;
        this.idBill = idBill;
        this.idFood = idFood;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getIdBill() {
        return idBill;
    }

    public int getIdFood() {
        return idFood;
    }

    public int getQuantity() {
        return quantity;
    }
}
