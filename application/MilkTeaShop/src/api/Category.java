package api;

import app.pattern.Api;
import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

	@NotNull
	public ArrayList<model.Category> getEnabledCategories() {
		ArrayList<model.Category> categories = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [EnabledCategory]");
			assert resultSet != null;
			while (resultSet.next()) {
				categories.add(new model.Category(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return categories;
	}

	@Nullable
	public ArrayList<model.Category> getCategories(){
		ArrayList<model.Category> categories = new ArrayList<>();
		try{
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Category]");
			assert resultSet != null;
			while(resultSet.next()){
				categories.add(new model.Category(resultSet));
			}
		} catch(SQLException e){
			AlertError.getInstance().showAndWait(e);
		}
		return categories;
	}

}
