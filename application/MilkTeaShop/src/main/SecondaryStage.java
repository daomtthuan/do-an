package main;

import javafx.stage.Stage;
import model.Account;
import model.Discount;
import org.jetbrains.annotations.Contract;

/**
 * The type Secondary stage.
 */
public final class SecondaryStage {
    private static SecondaryStage instance;
    private Stage stage;
    private Account account;
    private boolean ordering;
    private Discount discount;

    private SecondaryStage() {
        stage = new Stage();
        stage.setTitle("Milk Tea Shop - Customer");
        stage.setResizable(false);
        ordering = false;
    }

    /**
     * Gets instance.
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
     * Gets discount.
     *
     * @return the discount
     */
    @Contract(pure = true)
    public Discount getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Is ordering boolean.
     *
     * @return the boolean
     */
    @Contract(pure = true)
    public boolean isOrdering() {
        return ordering;
    }

    /**
     * Sets ordering.
     *
     * @param ordering the ordering
     */
    public void setOrdering(boolean ordering) {
        this.ordering = ordering;
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
