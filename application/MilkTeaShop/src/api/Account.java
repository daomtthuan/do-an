package api;

import app.alert.AlertError;
import app.pattern.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	@Nullable
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

	@Nullable
	public model.Account update(int id, String password, String name, boolean gender, String birthday, String address, String phone, String email, boolean status) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [updateAccount] ? , ? , ? , ? , ? , ? , ? , ? , ?", new Object[] {id, password, name, gender, birthday, address, phone, email, status});
			assert resultSet != null;
			return resultSet.next() ? new model.Account(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	@Nullable
	public model.Account login(@NotNull String who, String account, String password) {
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
