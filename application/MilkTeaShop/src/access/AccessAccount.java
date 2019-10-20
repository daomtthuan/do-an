package access;

import library.ErrorAlert;
import model.Account;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Data Access AccessAccount.
 */
public final class AccessAccount {
    private static AccessAccount instance;

    @Contract(pure = true)
    private AccessAccount() {
    }

    /**
     * Gets instance.
     *
     * @return {@link AccessAccount} the instance
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
     * Insert int.
     *
     * @param account  the account
     * @param name     the name
     * @param gender   the gender
     * @param birthday the birthday
     * @param address  the address
     * @param phone    the phone
     * @param email    the email
     * @param idRoll   the id roll
     * @return the int
     */
    @Nullable
    public Account insert(
            String account, String name, boolean gender, String birthday,
            String address, String phone, String email, int idRoll) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec InsertAccount ? , ? , ? , ? , ? , ? , ? , ?", new Object[]{account, name, gender, birthday, address, phone, email, idRoll});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Checking AdminLogin.
     *
     * @param who      the who login
     * @param name     the account name
     * @param password the password
     * @return {@link Account} if success, otherwise null.
     */
    @Nullable
    public Account login(String who, String name, String password) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec AdminLogin" + who + " ? , ?", new Object[]{name, password});
            assert resultSet != null;
            return resultSet.next() ? new Account(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
