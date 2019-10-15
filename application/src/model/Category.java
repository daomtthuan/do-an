package model;

import org.jetbrains.annotations.Contract;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
