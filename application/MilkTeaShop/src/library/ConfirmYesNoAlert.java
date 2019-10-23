package library;

import javafx.scene.control.Alert;

/**
 * The type Confirm yes no alert.
 */
public final class ConfirmYesNoAlert {
    private static ConfirmYesNoAlert instance;
    private Alert alert;

    private ConfirmYesNoAlert() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConfirmYesNoAlert getInstance() {
        if (instance == null) {
            setInstance(new ConfirmYesNoAlert());
        }
        return instance;
    }

    private static void setInstance(ConfirmYesNoAlert instance) {
        ConfirmYesNoAlert.instance = instance;
    }

    /**
     * Show and wait.
     *
     * @param header  the header
     * @param content the content
     */
    public void showAndWait(String header, String content) {
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
