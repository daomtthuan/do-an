package access;

import library.ErrorAlert;
import model.Discount;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Access discount.
 */
public final class AccessDiscount {
    private static AccessDiscount instance;

    @Contract(pure = true)
    private AccessDiscount() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AccessDiscount getInstance() {
        if (instance == null) {
            setInstance(new AccessDiscount());
        }
        return instance;
    }

    private static void setInstance(AccessDiscount instance) {
        AccessDiscount.instance = instance;
    }

    /**
     * Insert discount.
     *
     * @param name the name
     * @param sale the sale
     *
     * @return the discount
     */
    @Nullable
    public Discount insert(String name, double sale) {
        try (ResultSet resultSet = DataProvider.getInstance().execute("exec InsertDiscount ?,?", new Object[]{name, sale})) {
            assert resultSet != null;
            return resultSet.next() ? new Discount(resultSet) : null;
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    @Nullable
    public Discount check(String name) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec CheckDiscount ?", new Object[]{name});
            assert resultSet != null;
            return resultSet.next() ? new Discount(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
