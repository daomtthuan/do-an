package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRoll {
    private int id;
    private int idAccount;
    private int idRoll;

    @Contract(pure = true)
    public AccountRoll(int id, int idAccount, int idRoll) {
        this.id = id;
        this.idAccount = idAccount;
        this.idRoll = idRoll;
    }

    public AccountRoll(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.idAccount = data.getInt("idAccount");
            this.idRoll = data.getInt("idRoll");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    public int getId() {
        return id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public int getIdRoll() {
        return idRoll;
    }
}
