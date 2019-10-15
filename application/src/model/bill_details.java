package model;

public class bill_details {
    private int id;
    private int bill;
    private int food;
    private int quantity;

    public bill_details(int id, int bill, int food, int quantity) {
        this.id = id;
        this.bill = bill;
        this.food = food;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }
}
