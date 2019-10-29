package app.stage;

public final class DialogPrimaryStage extends Stage {
    private static DialogPrimaryStage instance;

    private DialogPrimaryStage() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Dialog");
    }

    public static DialogPrimaryStage getInstance() {
        if (instance == null) {
            setInstance(new DialogPrimaryStage());
        }
        return instance;
    }

    private static void setInstance(DialogPrimaryStage instance) {
        DialogPrimaryStage.instance = instance;
    }
}
