package main;

import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;

public final class DialogStage {
    private static Stage instance;

    @Contract(pure = true)
    private DialogStage() {
    }

    @Contract(pure = true)
    public static Stage getInstance() {
        if (instance == null) {
            setInstance(new Stage());
            instance.setTitle("Milk Tea Shop - Dialog");
            instance.setResizable(false);
        }
        return instance;
    }

    private static void setInstance(Stage instance) {
        DialogStage.instance = instance;
    }
}
