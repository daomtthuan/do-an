package main;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import library.ErrorAlert;
import model.Account;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

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
     * Gets instance.
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
            scene.getStylesheets().add("/view/style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
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
            scene.getStylesheets().add("/view/style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
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
