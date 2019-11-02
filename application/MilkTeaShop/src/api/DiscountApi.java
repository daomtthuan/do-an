package api;

import app.alert.AlertError;
import model.Discount;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountApi implements Api {
    private static DiscountApi instance;

    public static DiscountApi getInstance() {
        if (instance == null) {
            setInstance(new DiscountApi());
        }
        return instance;
    }

    private static void setInstance(DiscountApi instance) {
        DiscountApi.instance = instance;
    }

    public Discount insert(String name, double sale) {
        try (ResultSet resultSet = DataProvider.getInstance().execute("exec InsertDiscount ?,?", new Object[]{name, sale})) {
            assert resultSet != null;
            return resultSet.next() ? new Discount(resultSet) : null;
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

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
