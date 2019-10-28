package api;

import plugin.alert.AlertError;
import model.Category;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Access category.
 */
public final class ApiCategory {
    private static ApiCategory instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApiCategory getInstance() {
        if (instance == null) {
            setInstance(new ApiCategory());
        }
        return instance;
    }

    private static void setInstance(ApiCategory instance) {
        ApiCategory.instance = instance;
    }

    /**
     * Load array list.
     *
     * @return the array list
     */
    @NotNull
    @Contract(pure = true)
    public ArrayList<Category> getList() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("select * from Category");
            assert resultSet != null;
            while (resultSet.next()) {
                categories.add(new Category(resultSet));
            }
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
        return categories;
    }
}
