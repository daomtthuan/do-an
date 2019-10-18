package access;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import ui.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Data Access Account.
 */
public final class Account {
    private static Account instance;

    @Contract(pure = true)
    private Account() {
    }

    /**
     * Gets instance.
     *
     * @return {@link Account} the instance
     */
    @Contract(pure = true)
    public static Account getInstance() {
        if (instance == null) {
            setInstance(new Account());
        }
        return instance;
    }

    private static void setInstance(Account instance) {
        Account.instance = instance;
    }

    public int insert(int id, String name, int idInformation, int idRoll)  {
        try(ResultSet resultSet = DataProvider.getInstance().execute("result", new Object[]{id,name,idInformation,idRoll})){
            assert resultSet != null;
            return  resultSet.next() ? resultSet.getInt("result") : -1;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return -1;
        }

    }

    /**
     * Checking Login.
     *
     * @param name the account name
     * @param password the password
     * @return {@link model.Account} if success, otherwise null.
     */
    @Nullable
    public model.Account login(String name, String password) {
        try (ResultSet resultSet = DataProvider.getInstance().execute("exec ProcedureLogin ? , ?", new Object[]{name, password})) {
            assert resultSet != null;
            return resultSet.next() ? new model.Account(resultSet) : null;
        } catch (Exception e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }
}
