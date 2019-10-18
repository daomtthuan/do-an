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
    private int idRoll;

    @Contract(pure = true)
    public Account(int id, String name, String password, int idInformation, int idRoll) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.idInformation = idInformation;
        this.idRoll = idRoll;
    }

    public Account(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.password = data.getString("password");
            this.idInformation = data.getInt("idInformation");
            this.idRoll = data.getInt("idRoll");
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

    public int getIdRoll() {
        return idRoll;
    }
}
