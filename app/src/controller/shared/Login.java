package controller.shared;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public abstract class Login {
    @FXML
    private TextField account;
    @FXML
    private TextField password;

    @FXML
    public abstract void login(ActionEvent event) throws IOException;
}
