package api;

import app.alert.AlertError;
import model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountApi implements Api {
    private static AccountApi instance;

    public static AccountApi getInstance() {
        if (instance == null) {
            setInstance(new AccountApi());
        }
        return instance;
    }

    private static void setInstance(AccountApi instance) {
        AccountApi.instance = instance;
    }

    public Account insert(
            String account, int roll, String name, boolean gender, String birthday,
            String address, String phone, String email) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec InsertAccount ? , ? , ? , ? , ? , ? , ? , ?", new Object[]{account, roll, name, gender, birthday, address, phone, email});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

    public Account update(
            int id, String name, boolean gender, String birthday,
            String address, String phone, String email) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec UpdateAccount ? , ? , ? , ? , ? , ? , ?", new Object[]{id, name, gender, birthday, address, phone, email});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

    public Account update(int id, String password) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec UpdatePassword ? , ?", new Object[]{id, password});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

    public Account login(String who, String account, String password) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec Login" + who + " ? , ?", new Object[]{account, password});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }
}
