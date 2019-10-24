package access;

import model.TableFood;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

/**
 * The type Access table food.
 */
public final class AccessTableFood {
    private static AccessTableFood instance;

    @Contract(pure = true)
    private AccessTableFood() {
    }

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
