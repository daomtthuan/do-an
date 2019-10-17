package access;

import org.jetbrains.annotations.Contract;
import ui.ErrorAlert;

import java.sql.ResultSet;

public class Account {
    private static Account instance;

    @Contract(pure = true)
    private Account() {
    }

    @Contract(pure = true)
    public static Account getInstance() {
        return instance == null ? new Account() : instance;
    }

    public model.Account login(String name, String password) {
        try (ResultSet resultSet = DataProvider.getInstance().execute("exec ProcedureLogin ? , ?", new Object[]{name, password})) {
            return resultSet.next() ? new model.Account(resultSet) : null;
        } catch (Exception e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }
}
