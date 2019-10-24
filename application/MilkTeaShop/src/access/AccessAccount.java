package access;

import library.ErrorAlert;
import model.Account;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Access account.
 */
public final class AccessAccount {
    private static AccessAccount instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    @Contract(pure = true)
    public static AccessAccount getInstance() {
        if (instance == null) {
            setInstance(new AccessAccount());
        }
        return instance;
    }

    private static void setInstance(AccessAccount instance) {
        AccessAccount.instance = instance;
    }

    /**
     * Insert account.
     *
     * @param account  the account
     * @param roll     the roll
     * @param name     the name
     * @param gender   the gender
     * @param birthday the birthday
     * @param address  the address
     * @param phone    the phone
     * @param email    the email
     *
     * @return the account
     */
    @Nullable
    public Account insert(
            String account, int roll, String name, boolean gender, String birthday,
            String address, String phone, String email) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec InsertAccount ? , ? , ? , ? , ? , ? , ? , ?", new Object[]{account, roll, name, gender, birthday, address, phone, email});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Update account.
     *
     * @param id       the id
     * @param name     the name
     * @param gender   the gender
     * @param birthday the birthday
     * @param address  the address
     * @param phone    the phone
     * @param email    the email
     *
     * @return the account
     */
    @Nullable
    public Account update(
            int id, String name, boolean gender, String birthday,
            String address, String phone, String email) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec UpdateAccount ? , ? , ? , ? , ? , ? , ?", new Object[]{id, name, gender, birthday, address, phone, email});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Update account.
     *
     * @param id       the id
     * @param password the password
     *
     * @return the account
     */
    @Nullable
    public Account update(int id, String password) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec UpdatePassword ? , ?", new Object[]{id, password});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Update account.
     *
     * @param id   the id
     * @param roll the roll
     *
     * @return the account
     */
    @Nullable
    public Account update(int id, int roll) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec UpdateRoll ? , ?", new Object[]{id, roll});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Login account.
     *
     * @param who      the who
     * @param account  the account
     * @param password the password
     *
     * @return the account
     */
    @Nullable
    public Account login(String who, String account, String password) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec Login" + who + " ? , ?", new Object[]{account, password});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }
}
