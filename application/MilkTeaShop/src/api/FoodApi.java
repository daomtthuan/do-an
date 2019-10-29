package api;

import app.alert.AlertError;
import model.Food;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class FoodApi {
    private static FoodApi instance;

    public static FoodApi getInstance() {
        if (instance == null) {
            setInstance(new FoodApi());
        }
        return instance;
    }

    private static void setInstance(FoodApi instance) {
        FoodApi.instance = instance;
    }

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
