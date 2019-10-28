package app;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

/**
 * The type Dialog primary stage.
 */
public final class DialogPrimaryStage {
    private static DialogPrimaryStage instance;
    private Stage stage;

    private DialogPrimaryStage() {
        stage = new Stage();
        stage.setTitle("Milk Tea Shop - Dialog");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DialogPrimaryStage getInstance() {
        if (instance == null) {
            setInstance(new DialogPrimaryStage());
        }
        return instance;
    }

    private static void setInstance(DialogPrimaryStage instance) {
        DialogPrimaryStage.instance = instance;
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
}
