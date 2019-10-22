package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Account.
 */
public class Account {
    private int id;
    private String account;
    private String password;
    private int roll;
    private String name;
    private boolean gender;
    private String birthday;
    private String address;
    private String phone;
    private String email;

    /**
     * Instantiates a new Account.
     *
     * @param resultSet the result set
     */
    @Contract(pure = true)
    public Account(@NotNull ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            account = resultSet.getString("account");
            password = resultSet.getString("password");
            roll = resultSet.getInt("roll");
            name = resultSet.getString("name");
            gender = resultSet.getBoolean("gender");
            birthday = resultSet.getString("birthday");
            address = resultSet.getString("address");
            phone = resultSet.getString("phone");
            email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
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
     * Gets account.
     *
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets roll.
     *
     * @return the roll
     */
    public int getRoll() {
        return roll;
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
     * Is male boolean.
     *
     * @return the boolean
     */
    public boolean isMale() {
        return gender;
    }

    /**
     * Is fem male boolean.
     *
     * @return the boolean
     */
    public boolean isFemMale() {
        return !gender;
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
