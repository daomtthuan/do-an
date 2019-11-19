package api;

import app.alert.AlertError;
import app.pattern.Api;

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

	public ArrayList<model.Table> getEnabledTables() {
		ArrayList<model.Table> tables = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Table] where [status] > 0");
			while (resultSet.next()) {
				tables.add(new model.Table(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return tables;
	}

	public ArrayList<model.Table> getNotBusyTables() {
		ArrayList<model.Table> tables = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Table] where [status] = 1");
			while (resultSet.next()) {
				tables.add(new model.Table(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return tables;
	}

	public ArrayList<model.Table> getTables() {
		ArrayList<model.Table> tables = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Table]");
			assert resultSet != null;
			while (resultSet.next()) {
				tables.add(new model.Table(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return tables;
	}

	public model.Table insert(double x, double y) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertTable] ? , ?", new Object[] {x, y});
			assert resultSet != null;
			return resultSet.next() ? new model.Table(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Table update(int id, double x, double y) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [updateTable] ? , ? , ?", new Object[] {id, x, y});
			assert resultSet != null;
			return resultSet.next() ? new model.Table(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Table changeStatus(int id, int status) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [statusTable] ? , ?", new Object[] {id, status});
			assert resultSet != null;
			return resultSet.next() ? new model.Table(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Table delete(int id) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [deleteTable] ? ", new Object[] {id});
			assert resultSet != null;
			return resultSet.next() ? new model.Table(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Table changeTable(int idOldTable, int idNewTable) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [changeTable] ? , ?", new Object[] {idOldTable, idNewTable});
			assert resultSet != null;
			return resultSet.next() ? new model.Table(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
