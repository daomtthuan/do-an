package ui;

import javafx.scene.control.Alert;

public class WarningAlert {
    private static WarningAlert instance;
    private Alert alert;

    private WarningAlert() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
    }

    public static WarningAlert getInstance() {
        return instance == null ? new WarningAlert() : instance;
    }

    public void showAndWait(String message) {
        alert.setContentText(message);
        alert.showAndWait();
    }
}
