package plugin.alert;

import javafx.scene.control.Alert;

/**
 * The type Alert confirm yes no.
 */
public final class AlertConfirmYesNo {
    private static AlertConfirmYesNo instance;
    private Alert alert;

    private AlertConfirmYesNo() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AlertConfirmYesNo getInstance() {
        if (instance == null) {
            setInstance(new AlertConfirmYesNo());
        }
        return instance;
    }

    private static void setInstance(AlertConfirmYesNo instance) {
        AlertConfirmYesNo.instance = instance;
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
