package controller.customer;

import controller.Information;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import main.DialogStage;
import main.SecondaryStage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Register for Customer.
 */
public class Register extends Information implements Initializable {

    @Override
    protected void submit(ActionEvent event) {
        System.out.println("ha ha ha");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle("REGISTER");
        SecondaryStage.getInstance().getStage().hide();
        DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });
    }
}
