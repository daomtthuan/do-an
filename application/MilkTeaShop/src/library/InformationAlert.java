package library;

import javafx.scene.control.Alert;

/**
 * The type Information alert.
 */
public final class InformationAlert {
    private static InformationAlert instance;
    private Alert alert;

    private InformationAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static InformationAlert getInstance() {
        if (instance == null) {
            setInstance(new InformationAlert());
        }
        return instance;
    }

    private static void setInstance(InformationAlert instance) {
        InformationAlert.instance = instance;
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
