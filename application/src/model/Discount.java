package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Discount {
    private int id;
    private String name;
    private double sale;

    @Contract(pure = true)
    public Discount() {
    }

    @Contract(pure = true)
    public Discount(int id, String name, double sale) {
        this.id = id;
        this.name = name;
        this.sale = sale;
    }
    @Contract(pure = true)
    public Discount(@NotNull Discount discount){
        this.id = discount.id;
        this.name = discount.name;
        this.sale = discount.sale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSale() {
        return sale;
    }
}
