package app;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import model.Account;
import model.Discount;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

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
     * Sets scene.
     *
     * @param viewPath   the view path
     * @param controller the controller
     */
    public void setScene(String viewPath, Controller controller) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource(viewPath));
            view.setController(controller);
            Scene scene = new Scene(view.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets scene.
     *
     * @param viewPath the view path
     */
    public void setScene(String viewPath) {
        try {
            FXMLLoader view = new FXMLLoader(getClass().getResource(viewPath));
            Scene scene = new Scene(view.load());
            new JMetro(scene, Style.LIGHT);
            scene.getStylesheets().add("/style/style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
