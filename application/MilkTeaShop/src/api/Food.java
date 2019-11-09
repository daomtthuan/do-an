package api;

import app.pattern.Api;
import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Food implements Api {
	private static Food instance;

	public static Food getInstance() {
		if (instance == null) {
			setInstance(new Food());
		}
		return instance;
	}

	private static void setInstance(Food instance) {
		Food.instance = instance;
	}

	@NotNull
	public ArrayList<model.Food> getEnabledList(int idCategory) {
		ArrayList<model.Food> foods = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [GetEnabledFood] ?", new Object[] {idCategory});
			assert resultSet != null;
			while (resultSet.next()) {
				foods.add(new model.Food(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return foods;
	}
}
