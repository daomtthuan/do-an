package main;

import javafx.stage.Stage;
import model.Account;
import org.jetbrains.annotations.Contract;

/**
 * The Secondary stage.
 */
public final class SecondaryStage {
    private static Stage instance;
    private Account account;

    @Contract(pure = true)
    private SecondaryStage() {
    }

    /**
     * Gets instance Secondary stage.
     *
     * @return the instance
     */
    @Contract(pure = true)
    public static Stage getInstance() {
        if (instance == null) {
            setInstance(new Stage());
            instance.setTitle("Milk Tea Shop - Customer");
            instance.setResizable(false);
        }
        return instance;
    }

    private static void setInstance(Stage instance) {
        SecondaryStage.instance = instance;
    }

    /**
     * Gets account login on Secondary stage.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account on Secondary stage.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
