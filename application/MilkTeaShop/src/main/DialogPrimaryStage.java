package main;

import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;

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
}
