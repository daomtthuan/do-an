package api;

import app.alert.AlertError;
import app.pattern.Api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Category implements Api {
	private static Category instance;

	public static Category getInstance() {
		if (instance == null) {
			setInstance(new Category());
		}
		return instance;
	}

	private static void setInstance(Category instance) {
		Category.instance = instance;
	}

	public ArrayList<model.Category> getEnabledCategories() {
		ArrayList<model.Category> categories = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Category] where [status] = 1");
			assert resultSet != null;
			while (resultSet.next()) {
				categories.add(new model.Category(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return categories;
	}

	public ArrayList<model.Category> getCategories() {
		ArrayList<model.Category> categories = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Category]");
			assert resultSet != null;
			while (resultSet.next()) {
				categories.add(new model.Category(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return categories;
	}

	public model.Category insert(String name) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertCategory] ?", new Object[] {name});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Category update(int id, String name) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [updateCategory] ? , ?", new Object[] {id, name});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Category changeStatus(int id, boolean enabled) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [statusCategory] ? , ?", new Object[] {id, enabled});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Category delete(int id) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [deleteCategory] ? ", new Object[] {id});
			assert resultSet != null;
			return resultSet.next() ? new model.Category(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
