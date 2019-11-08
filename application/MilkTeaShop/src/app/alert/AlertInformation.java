package app.alert;

import javafx.scene.control.Alert;

public class AlertInformation {
    private static AlertInformation instance;
    private Alert alert;

    private AlertInformation() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
    }

    public static AlertInformation getInstance() {
        if (instance == null) {
            setInstance(new AlertInformation());
        }
        return instance;
    }

    private static void setInstance(AlertInformation instance) {
        AlertInformation.instance = instance;
    }

    public void showAndWait(String header, String content) {
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
