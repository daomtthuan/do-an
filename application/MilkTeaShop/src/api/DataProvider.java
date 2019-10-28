package api;

import plugin.alert.AlertError;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * The type Data provider.
 */
public final class DataProvider {
    private static DataProvider instance;
    private Connection connection;

    @Contract(pure = true)
    private DataProvider() {
        try (FileReader reader = new FileReader(new File("Config.properties"))) {
            Properties properties = new Properties();
            properties.load(reader);
            connection = DriverManager.getConnection(properties.getProperty("url"));
        } catch (Exception e) {
            AlertError.getInstance().showAndWait(e);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    @Contract(pure = true)
    public static DataProvider getInstance() {
        if (instance == null) {
            setInstance(new DataProvider());
        }
        return instance;
    }

    private static void setInstance(DataProvider instance) {
        DataProvider.instance = instance;
    }

    /**
     * Execute result set.
     *
     * @param query the query
     * @return the result set
     */
    @Nullable
    ResultSet execute(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Execute result set.
     *
     * @param query      the query
     * @param parameters the parameters
     * @return the result set
     */
    @Nullable
    ResultSet execute(@NotNull String query, @NotNull Object[] parameters) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setEscapeProcessing(true);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
            return null;
        }
    }

    /**
     * Close.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }
}
