package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Food {
    private int id;
    private String name;
    private int idCategory;
    private boolean status;

    @Contract(pure = true)
    public Food(int id, String name, int idCategory, boolean status) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public boolean isStatus() {
        return status;
    }
}
