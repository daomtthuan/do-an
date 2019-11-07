package app.primary;

import app.Stage;

public class PrimaryDialog extends Stage {
    private static PrimaryDialog instance;

    private PrimaryDialog() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Dialog");
        getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            close();
        });
    }

    public static PrimaryDialog getInstance() {
        if (instance == null) {
            setInstance(new PrimaryDialog());
        }
        return instance;
    }

    private static void setInstance(PrimaryDialog instance) {
        PrimaryDialog.instance = instance;
    }

    public void close() {
        PrimaryStage.getInstance().getStage().show();
        getStage().hide();
    }
}
