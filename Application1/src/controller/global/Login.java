package controller.global;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public abstract class Login {
    @FXML
    private TextField textAaccount;
    @FXML
    private TextField textPpassword;

    @FXML
    public abstract void login(ActionEvent event) throws IOException;
}
