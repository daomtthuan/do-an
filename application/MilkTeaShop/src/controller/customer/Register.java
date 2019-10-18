package controller.customer;

import controller.Information;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import main.Stage;

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
        Stage.getInstance().getSecondary().hide();
        Stage.getInstance().getDialog().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            Stage.getInstance().getDialog().hide();
            Stage.getInstance().getSecondary().show();
        });
    }
}
