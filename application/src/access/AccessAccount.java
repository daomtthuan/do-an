package access;

import org.jetbrains.annotations.Contract;

public class AccessAccount {
    private static AccessAccount instance;

    @Contract(pure = true)
    private AccessAccount() {}

    @Contract(pure = true)
    public static AccessAccount getInstance() {
        return instance == null ? new AccessAccount() : instance;
    }
}
