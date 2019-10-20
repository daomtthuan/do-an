package library;

import javafx.scene.control.Alert;

/**
 * The type Warning alert.
 */
public final class WarningAlert {
    private static WarningAlert instance;
    private Alert alert;

    private WarningAlert() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static WarningAlert getInstance() {
        if (instance == null) {
            setInstance(new WarningAlert());
        }
        return instance;
    }

    private static void setInstance(WarningAlert instance) {
        WarningAlert.instance = instance;
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
