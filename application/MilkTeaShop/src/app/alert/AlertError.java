package app.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AlertError {
    private static AlertError instance;

    private Alert alert;
    private StringWriter stringWriter;
    private PrintWriter printWriter;
    private TextArea textArea;

    private AlertError() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        VBox dialogPaneContent = new VBox();
        Label label = new Label("Stack Trace:");
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        textArea = new TextArea();
        dialogPaneContent.getChildren().addAll(label, textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
    }

    public static AlertError getInstance() {
        if (instance == null) {
            setInstance(new AlertError());
        }
        return instance;
    }

    private static void setInstance(AlertError instance) {
        AlertError.instance = instance;
    }

    public void showAndWait(@NotNull Exception e) {
        e.printStackTrace(printWriter);
        textArea.setText(stringWriter.toString());
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }
}
