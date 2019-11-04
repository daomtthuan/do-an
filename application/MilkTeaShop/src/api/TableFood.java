package api;

import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class TableFood implements Api {
	private static TableFood instance;

	public static TableFood getInstance() {
		if (instance == null) {
			setInstance(new TableFood());
		}
		return instance;
	}

	private static void setInstance(TableFood instance) {
		TableFood.instance = instance;
	}

	@NotNull
	public ArrayList<model.TableFood> getEnabledList() {
		ArrayList<model.TableFood> tableFoods = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from EnabledTableFood");
			assert resultSet != null;
			while (resultSet.next()) {
				tableFoods.add(new model.TableFood(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return tableFoods;
	}
}
