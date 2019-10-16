package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Food {
    private int id;
    private String name;
    private Category category;
    private boolean status;

    @Contract(pure = true)
    public Food() {
    }

    @Contract(pure = true)
    public Food(int id, String name, Category category, boolean status) {
        this.id = id;
        this.name = name;
        this.category = new Category(category);
        this.status = status;
    }

    @Contract(pure = true)
    public Food(@NotNull Food food) {
        this.id = food.id;
        this.name = food.name;
        this.category = new Category(food.category);
        this.status = food.status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isStatus() {
        return status;
    }
}
