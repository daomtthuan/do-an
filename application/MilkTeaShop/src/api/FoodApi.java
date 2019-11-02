package api;

import app.alert.AlertError;
import model.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodApi implements Api {
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
