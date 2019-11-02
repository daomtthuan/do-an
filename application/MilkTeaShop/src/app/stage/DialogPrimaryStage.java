package app.stage;

public final class DialogPrimaryStage extends Stage {
    private static DialogPrimaryStage instance;

    private DialogPrimaryStage() {
        setStage(new javafx.stage.Stage());
        getStage().setTitle("Milk Tea Shop - Dialog");
        getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            PrimaryStage.getInstance().getStage().show();
            getStage().hide();
        });
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
