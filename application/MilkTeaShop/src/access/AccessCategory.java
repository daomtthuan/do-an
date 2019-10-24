package access;

import model.Category;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Access category.
 */
public final class AccessCategory {
    private static AccessCategory instance;

    @Contract(pure = true)
    private AccessCategory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AccessCategory getInstance() {
        if (instance == null) {
            setInstance(new AccessCategory());
        }
        return instance;
    }

    private static void setInstance(AccessCategory instance) {
        AccessCategory.instance = instance;
    }

    /**
     * Load array list.
     *
     * @return the array list
     */
    @NotNull
    @Contract(pure = true)
    public ArrayList<Category> load() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = DataProvider.getInstance().execute("select * from Category");
            assert resultSet != null;
            while (resultSet.next()) {
                categories.add(new Category(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
