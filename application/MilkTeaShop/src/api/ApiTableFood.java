package api;

/**
 * The type Access table food.
 */
public final class ApiTableFood {
    private static ApiTableFood instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApiTableFood getInstance() {
        if (instance == null) {
            setInstance(new ApiTableFood());
        }
        return instance;
    }

    private static void setInstance(ApiTableFood instance) {
        ApiTableFood.instance = instance;
    }

//    public ArrayList<TableFood> load() {
//        ResultSet resultSet = DataProvider.getInstance().execute()
//    }
}
