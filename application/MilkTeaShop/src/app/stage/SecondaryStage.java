package app.stage;

import model.Account;
import model.Discount;
import org.jetbrains.annotations.Contract;

public class SecondaryStage extends Stage {
    private static SecondaryStage instance;
    private Account account;
    private boolean ordering;
    private Discount discount;

    private SecondaryStage() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Customer");
        ordering = false;
    }

    public static SecondaryStage getInstance() {
        if (instance == null) {
            setInstance(new SecondaryStage());
        }
        return instance;
    }

    private static void setInstance(SecondaryStage instance) {
        SecondaryStage.instance = instance;
    }

    @Contract(pure = true)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Contract(pure = true)
    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Contract(pure = true)
    public boolean isOrdering() {
        return ordering;
    }

    public void setOrdering(boolean ordering) {
        this.ordering = ordering;
    }
}
