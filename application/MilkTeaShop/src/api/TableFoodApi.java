package api;

public final class TableFoodApi {
    private static TableFoodApi instance;

    public static TableFoodApi getInstance() {
        if (instance == null) {
            setInstance(new TableFoodApi());
        }
        return instance;
    }

    private static void setInstance(TableFoodApi instance) {
        TableFoodApi.instance = instance;
    }

//    public ArrayList<TableFood> load() {
//        ResultSet resultSet = DataProvider.getInstance().execute()
//    }
}
