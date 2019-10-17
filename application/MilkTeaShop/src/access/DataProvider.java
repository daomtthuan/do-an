package access;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ui.ErrorAlert;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class DataProvider {
    private static DataProvider instance;
    private Connection connection;

    @Contract(pure = true)
    private DataProvider() {
        try (FileReader reader = new FileReader(new File("Config.properties"))) {
            Properties properties = new Properties();
            properties.load(reader);
            connection = DriverManager.getConnection(properties.getProperty("url"));
        } catch (Exception e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    @Contract(pure = true)
    public static DataProvider getInstance() {
        return instance == null ? new DataProvider() : instance;
    }

    ResultSet execute(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    ResultSet execute(@NotNull String query, @NotNull Object[] parameters) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setEscapeProcessing(true);
            for (int i = 0; i < parameters.length; i++)
                statement.setObject(i + 1, parameters[i]);
            return statement.executeQuery();
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }
}
