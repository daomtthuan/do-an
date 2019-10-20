package access;

import org.jetbrains.annotations.Contract;

import java.sql.ResultSet;

public final class Category {
    private  Category instance;

    @Contract(pure = true)
    public Category() {
    }

    @Contract(pure = true)
    public Category(Category instance) {
        this.instance = instance;
    }

    public Category getInstance() {
        if(instance == null){
            setInstance(new Category());
        }
        return instance;
    }

    private void setInstance(Category instance) {
        this.instance = instance;
    }

    public void insert(int id, String name){
        ResultSet resultSet = DataProvider.getInstance().execute("exec ProcedureInsertCategory ?",new Object[]{id,name});
    }
}
