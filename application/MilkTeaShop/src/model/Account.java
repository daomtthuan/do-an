package model;

import app.alert.AlertError;
import app.pattern.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class
Account implements Model {
	private int id;
	private String account;
	private String password;
	private int roll;
	private String name;
	private boolean gender;
	private String birthday;
	private String address;
	private String phone;
	private String email;
	private boolean status;

	public Account(ResultSet resultSet) {
		try {
			id = resultSet.getInt("id");
			account = resultSet.getString("account");
			password = resultSet.getString("password");
			roll = resultSet.getInt("roll");
			name = resultSet.getString("name");
			gender = resultSet.getBoolean("gender");
			birthday = resultSet.getString("birthday");
			address = resultSet.getString("address");
			phone = resultSet.getString("phone");
			email = resultSet.getString("email");
			status = resultSet.getBoolean("status");
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public int getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public int getRoll() {
		return roll;
	}

	public String getName() {
		return name;
	}

	public boolean isMale() {
		return gender;
	}

	public boolean isFemMale() {
		return !gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public boolean isEnabled() {
		return status;
	}

	@Override
	public String toString() {
		return account + " - " + name;
	}
}
