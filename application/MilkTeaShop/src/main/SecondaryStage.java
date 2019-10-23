package main;

import javafx.stage.Stage;
import model.Account;
import model.Bill;
import org.jetbrains.annotations.Contract;

/**
 * The type Secondary stage.
 */
public final class SecondaryStage {
    private static SecondaryStage instance;
    private Stage stage;
    private Account account;
    private Bill bill;
    private boolean ordering;

    private SecondaryStage() {
        stage = new Stage();
        stage.setTitle("Milk Tea Shop - Customer");
        stage.setResizable(false);
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
     * Gets bill.
     *
     * @return the bill
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * Sets bill.
     *
     * @param bill the bill
     */
    public void setBill(Bill bill) {
        this.bill = bill;
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
