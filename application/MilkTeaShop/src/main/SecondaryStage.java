package main;

import javafx.stage.Stage;
import model.Account;
import org.jetbrains.annotations.Contract;

/**
 * The Secondary stage.
 */
public final class SecondaryStage {
    private static SecondaryStage instance;
    private Stage stage;
    private Account account;

    private SecondaryStage() {
        stage = new Stage();
        stage.setTitle("Milk Tea Shop - Customer");
        stage.setResizable(false);
    }

    /**
     * Gets instance Secondary stage.
     *
     * @return the instance
     */
    public static SecondaryStage getInstance() {
        if (instance == null) {
            setInstance(new SecondaryStage());
        }
        return instance;
    }

    private static void setInstance(SecondaryStage instance) {
        SecondaryStage.instance = instance;
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
    public void setStage(Stage stage) {
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
