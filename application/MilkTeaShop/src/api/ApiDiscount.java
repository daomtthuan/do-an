package api;

import plugin.alert.AlertError;
import model.Discount;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Access discount.
 */
public final class ApiDiscount {
    private static ApiDiscount instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApiDiscount getInstance() {
        if (instance == null) {
            setInstance(new ApiDiscount());
        }
        return instance;
    }

    private static void setInstance(ApiDiscount instance) {
        ApiDiscount.instance = instance;
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
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Check discount.
     *
     * @param name the name
     *
     * @return the discount
     */
    @Nullable
    public Discount check(String name) {
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec CheckDiscount ?", new Object[]{name});
            assert resultSet != null;
            return resultSet.next() ? new Discount(resultSet) : null;
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }
}
