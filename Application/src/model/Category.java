package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Category {

    private int id;
    private String name;

    @Contract(pure = true)
    public Category() {
    }

    @Contract(pure = true)
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Contract(pure = true)
    public Category(@NotNull Category category) {
        this.id = category.id;
        this.name = category.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
