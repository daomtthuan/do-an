package access;

/**
 * The type Access table food.
 */
public final class AccessTableFood {
    private static AccessTableFood instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AccessTableFood getInstance() {
        if (instance == null) {
            setInstance(new AccessTableFood());
        }
        return instance;
    }

    private static void setInstance(AccessTableFood instance) {
        AccessTableFood.instance = instance;
    }

//    public ArrayList<TableFood> load() {
//        ResultSet resultSet = DataProvider.getInstance().execute()
//    }
}
