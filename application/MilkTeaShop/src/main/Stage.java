package main;

import org.jetbrains.annotations.Contract;

/**
 * The type Stage.
 */
public final class Stage {
    private static Stage instance;
    private javafx.stage.Stage primary;
    private javafx.stage.Stage secondary;
    private javafx.stage.Stage dialog;

    @Contract(pure = true)
    private Stage() {
    }

    /**
     * Gets Stage instance.
     *
     * @return the instance
     */
    @Contract(pure = true)
    public static Stage getInstance() {
        if (instance == null) {
            setInstance(new Stage());
        }
        return instance;
    }

    private static void setInstance(Stage instance) {
        Stage.instance = instance;
    }

    /**
     * Gets primary Stage.
     *
     * @return the primary Stage
     */
    @Contract(pure = true)
    public javafx.stage.Stage getPrimary() {
        return primary;
    }

    /**
     * Sets primary Stage.
     *
     * @param primary the stage
     */
    void setPrimary(javafx.stage.Stage primary) {
        this.primary = primary;
        this.primary.setTitle("Milk Tea Shop");
        this.primary.setResizable(false);
    }

    /**
     * Gets secondary Stage.
     *
     * @return the secondary Stage
     */
    @Contract(pure = true)
    public javafx.stage.Stage getSecondary() {
        if (secondary == null) {
            setSecondary(new javafx.stage.Stage());
            secondary.setTitle("Milk Tea Shop - Customer");
            secondary.setResizable(false);
        }
        return secondary;
    }

    private void setSecondary(javafx.stage.Stage secondary) {
        this.secondary = secondary;
    }

    /**
     * Gets dialog Stage.
     *
     * @return the dialog Stage
     */
    @Contract(pure = true)
    public javafx.stage.Stage getDialog() {
        if (dialog == null) {
            setDialog(new javafx.stage.Stage());
            this.primary.setTitle("Milk Tea Shop - Dialog");
            dialog.setResizable(false);
        }
        return dialog;
    }

    private void setDialog(javafx.stage.Stage dialog) {
        this.dialog = dialog;
    }
}
