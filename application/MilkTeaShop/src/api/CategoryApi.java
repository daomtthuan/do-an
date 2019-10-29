package api;

import app.alert.AlertError;
import model.Category;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class CategoryApi {
    private static CategoryApi instance;

    public static CategoryApi getInstance() {
        if (instance == null) {
            setInstance(new CategoryApi());
        }
        return instance;
    }

    private static void setInstance(CategoryApi instance) {
        CategoryApi.instance = instance;
    }

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
