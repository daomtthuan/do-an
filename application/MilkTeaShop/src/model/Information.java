package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import library.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Information.
 */
public class Information {
    private int id;
    private String name;
    private boolean gender;
    private String birthday;
    private String address;
    private String phone;
    private String email;

    /**
     * Instantiates a new Information.
     *
     * @param id       the id
     * @param name     the name
     * @param gender   the gender
     * @param birthday the birthday
     * @param address  the address
     * @param phone    the phone
     * @param email    the email
     */
    @Contract(pure = true)
    public Information(int id, String name, boolean gender, String birthday, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Instantiates a new Information.
     *
     * @param data the data
     */
    public Information(@NotNull ResultSet data) {
        try {
            this.id = data.getInt("id");
            this.name = data.getString("name");
            this.gender = data.getBoolean("gender");
            this.birthday = data.getString("birthday");
            this.address = data.getString("address");
            this.phone = data.getString("phone");
            this.email = data.getString("email");
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
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
     * Is gender boolean.
     *
     * @return the boolean
     */
    public boolean isGender() {
        return gender;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}

