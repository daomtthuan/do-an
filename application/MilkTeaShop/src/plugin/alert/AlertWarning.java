package plugin.alert;

import javafx.scene.control.Alert;

/**
 * The type Alert warning.
 */
public final class AlertWarning {
    private static AlertWarning instance;
    private Alert alert;

    private AlertWarning() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AlertWarning getInstance() {
        if (instance == null) {
            setInstance(new AlertWarning());
        }
        return instance;
    }

    private static void setInstance(AlertWarning instance) {
        AlertWarning.instance = instance;
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
