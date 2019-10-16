package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TableFood {
    private int id;
    private boolean status;
    private int x;
    private int y;

    @Contract(pure = true)
    public TableFood() {
    }

    @Contract(pure = true)
    public TableFood(int id, boolean status, int x, int y) {
        this.id = id;
        this.status = status;
        this.x = x;
        this.y = y;
    }

    @Contract(pure = true)
    public TableFood(@NotNull TableFood tableFood) {
        this.id = tableFood.id;
        this.status = tableFood.status;
        this.x = tableFood.x;
        this.y = tableFood.y;
    }

    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
