package api;

import app.alert.AlertError;
import app.pattern.Api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account implements Api {
	private static Account instance;

	public static Account getInstance() {
		if (instance == null) {
			setInstance(new Account());
		}
		return instance;
	}

	private static void setInstance(Account instance) {
		Account.instance = instance;
	}

	public ArrayList<model.Account> getAccounts(int roll) {
		ArrayList<model.Account> accounts = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Account] where [roll] = " + roll);
			assert resultSet != null;
			while (resultSet.next()) {
				accounts.add(new model.Account(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return accounts;
	}

	public model.Account insert(String account, String password, int roll, String name, boolean gender, String birthday, String address, String phone, String email) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertAccount] ? , ? , ? , ? , ? , ? , ? , ? , ?", new Object[] {account, password, roll, name, gender, birthday, address, phone, email});
			assert resultSet != null;
			return resultSet.next() ? new model.Account(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Account update(int id, String password, String name, boolean gender, String birthday, String address, String phone, String email) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [updateAccount] ? , ? , ? , ? , ? , ? , ? , ?", new Object[] {id, password, name, gender, birthday, address, phone, email});
			assert resultSet != null;
			return resultSet.next() ? new model.Account(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Account changeStatus(int id, boolean enabled) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [statusAccount] ? , ?", new Object[] {id, enabled});
			assert resultSet != null;
			return resultSet.next() ? new model.Account(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Account delete(int id) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [deleteAccount] ? ", new Object[] {id});
			assert resultSet != null;
			return resultSet.next() ? new model.Account(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Account login(String who, String account, String password) {
		if (who.equals("Admin") || who.equals("Customer")) {
			try {
				ResultSet resultSet = DataProvider.getInstance().execute("exec [login" + who + "] ? , ?", new Object[] {account, password});
				assert resultSet != null;
				return resultSet.next() ? new model.Account(resultSet) : null;
			} catch (SQLException e) {
				AlertError.getInstance().showAndWait(e);
				return null;
			}
		} else {
			return null;
		}
	}
}
