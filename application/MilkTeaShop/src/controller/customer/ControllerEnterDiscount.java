package controller.customer;

import javafx.fxml.Initializable;
import main.DialogStage;
import main.SecondaryStage;

import java.net.URL;
import java.util.ResourceBundle;

public final class ControllerEnterDiscount implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecondaryStage.getInstance().getStage().hide();
        DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
