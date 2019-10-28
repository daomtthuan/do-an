package plugin.alert;

import javafx.scene.control.Alert;

/**
 * The type Alert information.
 */
public final class AlertInformation {
    private static AlertInformation instance;
    private Alert alert;

    private AlertInformation() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AlertInformation getInstance() {
        if (instance == null) {
            setInstance(new AlertInformation());
        }
        return instance;
    }

    private static void setInstance(AlertInformation instance) {
        AlertInformation.instance = instance;
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
