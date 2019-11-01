package model;

import app.alert.AlertError;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            AlertError.getInstance().showAndWait(e);
        }
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return gender;
    }

    public boolean isFemMale() {
        return !gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
