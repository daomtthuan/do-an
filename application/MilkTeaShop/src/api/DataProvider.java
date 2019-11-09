package api;

import app.alert.AlertError;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public final class DataProvider {
	private static DataProvider instance;
	private Connection connection;

	private DataProvider() {
		try (FileReader reader = new FileReader(new File("Config.properties"))) {
			Properties properties = new Properties();
			properties.load(reader);
			connection = DriverManager.getConnection(properties.getProperty("url"));
		} catch (Exception e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	public static DataProvider getInstance() {
		if (instance == null) {
			setInstance(new DataProvider());
		}
		return instance;
	}

	private static void setInstance(DataProvider instance) {
		DataProvider.instance = instance;
	}

	@Nullable ResultSet execute(String query) {
		try {
			Statement statement = connection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	@Nullable ResultSet execute(String query, Object[] parameters) {
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setEscapeProcessing(true);
			for (int i = 0; i < (parameters != null ? parameters.length : 0); i++) {
				statement.setObject(i + 1, parameters[i]);
			}
			return statement.executeQuery();
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}
}
