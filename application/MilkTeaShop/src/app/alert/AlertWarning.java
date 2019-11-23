package app.alert;

import javafx.scene.control.Alert;

public class AlertWarning {
    private static AlertWarning instance;
    private Alert alert;

    private AlertWarning() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
    }

    public static AlertWarning getInstance() {
        if (instance == null) {
            setInstance(new AlertWarning());
        }
        return instance;
    }

    private static void setInstance(AlertWarning instance) {
        AlertWarning.instance = instance;
    }

    public void showAndWait(String header, String content) {
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
