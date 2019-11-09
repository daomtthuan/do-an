package app.secondary;

import app.Stage;
import javafx.scene.image.Image;

public class SecondaryDialog extends Stage {
    private static SecondaryDialog instance;

    private SecondaryDialog() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Customer - Dialog");
        getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            close();
        });
        getStage().getIcons().add(new Image(getClass().getResourceAsStream("/asset/brand/Logo.png")));
    }

    public static SecondaryDialog getInstance() {
        if (instance == null) {
            setInstance(new SecondaryDialog());
        }
        return instance;
    }

    private static void setInstance(SecondaryDialog instance) {
        SecondaryDialog.instance = instance;
    }

    public void close() {
        SecondaryStage.getInstance().getStage().show();
        getStage().hide();
    }
}
