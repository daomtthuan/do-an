package api;

import app.alert.AlertError;
import app.pattern.Api;

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

	public ArrayList<model.Food> getFoods(int idCategory) {
		ArrayList<model.Food> foods = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Food] where [idCategory] = " + idCategory);
			assert resultSet != null;
			while (resultSet.next()) {
				foods.add(new model.Food(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return foods;
	}

	public ArrayList<model.Food> getEnabledFoods(int idCategory) {
		ArrayList<model.Food> foods = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Food] where [idCategory] = " + idCategory + " and [status] = 1");
			assert resultSet != null;
			while (resultSet.next()) {
				foods.add(new model.Food(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return foods;
	}

	public model.Food insert(String name, int idCategory, double price) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertFood] ? , ? , ?", new Object[] {name, idCategory, price});
			assert resultSet != null;
			return resultSet.next() ? new model.Food(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Category update(int id, String name, int idCategory, double price) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [updateFood] ? , ? , ? , ?", new Object[] {id, name, idCategory, price});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Category changeStatus(int id, boolean enabled) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [statusFood] ? , ?", new Object[] {id, enabled});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Category delete(int id) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [deleteFood] ? ", new Object[] {id});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
