package access;

import library.ErrorAlert;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataProvider {
    private static DataProvider instance;
    private Connection connection;

    @Contract(pure = true)
    private DataProvider() {
        File file = new File("Config.properties");
        try (FileReader reader = new FileReader(file)) {
            Properties properties = new Properties();
            properties.load(reader);
            connection = DriverManager.getConnection("" +
                    properties.getProperty("driver") + ":" +
                    properties.getProperty("server") + "://" +
                    properties.getProperty("host") + ":" +
                    properties.getProperty("port") + ";databaseName=" +
                    properties.getProperty("database") + ";" +
                    "user=sa;password=sa;");
        } catch (IOException | SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }

    @Contract(pure = true)
    public static DataProvider getInstance() {
        return instance == null ? new DataProvider() : instance;
    }

    public ResultSet execute(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorAlert.getInstance().showAndWait(e);
            return null;
        }
    }

    public ResultSet execute(@NotNull String query, Object[] parameters) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setEscapeProcessing(true);
            int index = 1;
            for (Object parameter : parameters)
                statement.setObject(index++, parameter);
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
