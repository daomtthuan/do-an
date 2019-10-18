package main;

import javafx.stage.Stage;
import model.Account;
import org.jetbrains.annotations.Contract;

/**
 * Primary stage.
 */
public final class PrimaryStage {
    private static PrimaryStage instance;
    private Stage stage;
    private Account account;

    @Contract(pure = true)
    private PrimaryStage() {
    }

    /**
     * Gets instance Primary Stage.
     *
     * @return the instance
     */
    @Contract(pure = true)
    public static PrimaryStage getInstance() {
        return instance;
    }

    /**
     * Sets instance Primary Stage.
     *
     * @param instance the instance
     */
    static void setInstance(PrimaryStage instance) {
        PrimaryStage.instance = instance;
        PrimaryStage.instance.setTitle("Milk Tea Shop");
        PrimaryStage.instance.setResizable(false);
    }

    /**
     * Gets account login on Primary Stage.
     *
     * @return the account
     */
    @Contract(pure = true)
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account login on Primary Stage.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
