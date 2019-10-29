package app.stage;

public final class DialogSecondaryStage extends Stage {
    private static DialogSecondaryStage instance;

    private DialogSecondaryStage() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Dialog");
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
