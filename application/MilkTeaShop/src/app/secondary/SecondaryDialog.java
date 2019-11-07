package app.secondary;

import app.Stage;

public class SecondaryDialog extends Stage {
    private static SecondaryDialog instance;

    private SecondaryDialog() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Dialog");
        getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            close();
        });
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
