package access;

import org.jetbrains.annotations.Contract;

public class DataProvider {
    private static DataProvider instance;

    @Contract(pure = true)
    private DataProvider() {
    }

    @Contract(pure = true)
    public static DataProvider getInstance() {
        return instance == null ? new DataProvider() : instance;
    }
}
