package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Roll {
    private int id;
    private String name;

    @Contract(pure = true)
    public Roll() {
    }

    @Contract(pure = true)
    public Roll(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Contract(pure = true)
    public Roll(@NotNull Roll roll) {
        this.id = roll.id;
        this.name = roll.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
