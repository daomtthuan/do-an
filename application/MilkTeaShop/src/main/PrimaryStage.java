package main;

import javafx.stage.Stage;
import model.Account;
import org.jetbrains.annotations.Contract;

/**
 * The type Primary stage.
 */
public final class PrimaryStage {
    private static PrimaryStage instance;
    private Stage stage;
    private Account account;

    @Contract(pure = true)
    private PrimaryStage() {
    }

    /**
     * Gets instance Primary stage.
     *
     * @return the instance
     */
    public static PrimaryStage getInstance() {
        if (instance == null) {
            setInstance(new PrimaryStage());
        }
        return instance;
    }

    private static void setInstance(PrimaryStage instance) {
        PrimaryStage.instance = instance;
    }

    /**
     * Gets stage.
     *
     * @return the stage
     */
    @Contract(pure = true)
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets stage.
     *
     * @param stage the stage
     */
    void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets account.
     *
     * @return the account
     */
    @Contract(pure = true)
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
