package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Roll {
    private int id;
    private String name;

    @Contract(pure = true)
    public Roll(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Roll(ResultSet data) throws SQLException {
        this.id = data.getInt("id");
        this.name = data.getString("name");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
