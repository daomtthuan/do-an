package api;

import app.pattern.Api;
import app.alert.AlertError;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Table implements Api {
	private static Table instance;

	public static Table getInstance() {
		if (instance == null) {
			setInstance(new Table());
		}
		return instance;
	}

	private static void setInstance(Table instance) {
		Table.instance = instance;
	}

	@NotNull
	public ArrayList<model.Table> getEnabledTables() {
		ArrayList<model.Table> tables = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [EnabledTable]");
			assert resultSet != null;
			while (resultSet.next()) {
				tables.add(new model.Table(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return tables;
	}

	@NotNull
	public ArrayList<model.Table> getTables(){
		ArrayList<model.Table> tables = new ArrayList<>();
		try{
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Table]");
			assert resultSet != null;
			while(resultSet.next()){
				tables.add(new model.Table(resultSet));
			}
		} catch(SQLException e){
			AlertError.getInstance().showAndWait(e);
		}
		return tables;
	}
}
