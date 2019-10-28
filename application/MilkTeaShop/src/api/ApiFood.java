package api;

import plugin.alert.AlertError;
import model.Food;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Access food.
 */
public final class ApiFood {
    private static ApiFood instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApiFood getInstance() {
        if (instance == null) {
            setInstance(new ApiFood());
        }
        return instance;
    }

    private static void setInstance(ApiFood instance) {
        ApiFood.instance = instance;
    }

    /**
     * Gets list.
     *
     * @param idCategory the id category
     *
     * @return the list
     */
    @NotNull
    @Contract(pure = true)
    public ArrayList<Food> getList(int idCategory) {
        ArrayList<Food> foods = new ArrayList<>();
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("exec GetFood ?", new Object[]{idCategory});
            assert resultSet != null;
            while (resultSet.next()) {
                foods.add(new Food(resultSet));
            }
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
        return foods;
    }
}
