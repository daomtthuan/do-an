package app.stage;

public class DialogSecondaryStage extends Stage {
    private static DialogSecondaryStage instance;

    private DialogSecondaryStage() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Dialog");
        getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            SecondaryStage.getInstance().getStage().show();
            getStage().hide();
        });
    }

    public static DialogSecondaryStage getInstance() {
        if (instance == null) {
            setInstance(new DialogSecondaryStage());
        }
        return instance;
    }

    private static void setInstance(DialogSecondaryStage instance) {
        DialogSecondaryStage.instance = instance;
    }
}
