package app.alert;

import javafx.scene.control.Alert;

public class AlertConfirmYesNo {
    private static AlertConfirmYesNo instance;
    private Alert alert;

    private AlertConfirmYesNo() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
    }

    public static AlertConfirmYesNo getInstance() {
        if (instance == null) {
            setInstance(new AlertConfirmYesNo());
        }
        return instance;
    }

    private static void setInstance(AlertConfirmYesNo instance) {
        AlertConfirmYesNo.instance = instance;
    }

    public void showAndWait(String header, String content) {
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
