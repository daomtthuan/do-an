package main;

import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;

/**
 * The type Dialog stage.
 */
public final class DialogStage {
    private static DialogStage instance;
    private Stage stage;

    private DialogStage() {
        stage = new Stage();
        stage.setTitle("Milk Tea Shop - Dialog");
        stage.setResizable(false);
    }

    /**
     * Gets instance Dialog stage.
     *
     * @return the instance
     */
    public static DialogStage getInstance() {
        if (instance == null) {
            setInstance(new DialogStage());
        }
        return instance;
    }

    private static void setInstance(DialogStage instance) {
        DialogStage.instance = instance;
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
