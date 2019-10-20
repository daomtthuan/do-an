package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type AccessAccount.
 */
public class Account {
    private int id;
    private String name;
    private int idInformation;
    private int idRoll;

    /**
     * Instantiates a new AccessAccount.
     *
     * @param id            the id
     * @param name          the name
     * @param idInformation the id information
     * @param idRoll        the id roll
     */
    @Contract(pure = true)
    public Account(int id, String name, int idInformation, int idRoll) {
        this.id = id;
        this.name = name;
        this.idInformation = idInformation;
        this.idRoll = idRoll;
    }

    /**
     * Instantiates a new AccessAccount.
     *
     * @param data the data
     */
    public Account(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.idInformation = data.getInt("idInformation");
            this.idRoll = data.getInt("idRoll");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    public Account() {
        this.idRoll = 0;
        this.name = "guest";
        idRoll = 0;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id information.
     *
     * @return the id information
     */
    public int getIdInformation() {
        return idInformation;
    }

    /**
     * Gets id roll.
     *
     * @return the id roll
     */
    public int getIdRoll() {
        return idRoll;
    }
}
