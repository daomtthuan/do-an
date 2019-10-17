package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private int id;
    private String name;
    private String password;
    private int idInformation;

    @Contract(pure = true)
    public Account(int id, String name, String password, int idInformation) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.idInformation = idInformation;
    }

    public Account(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.password = data.getString("password");
            this.idInformation = data.getInt("idInformation");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getIdInformation() {
        return idInformation;
    }
}
