package access;

import org.jetbrains.annotations.Contract;
import ui.ErrorAlert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Information.
 */
public final class Information {

    private Information instance;

    @Contract(pure = true)
    private Information() {
    }

    public int insert(String name, boolean gender, String birthday, String address, int phone, String email) {
        try (ResultSet resultSet = DataProvider.getInstance().execute(
                "exec ProcedureInsertInformation ? , ? , ? , ? , ? , ?",
                new Object[]{name, gender, birthday, address, phone, email})) {
            assert resultSet != null;
            return resultSet.next() ? resultSet.getInt("id") : -1;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return -1;
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public Information getInstance() {
        if (instance == null) {
            setInstance(new Information());
        }
        return instance;
    }

    private void setInstance(Information instance) {
        this.instance = instance;
    }
}
