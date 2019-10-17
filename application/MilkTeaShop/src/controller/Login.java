package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Login {
    @FXML
    private TextField name;
    @FXML
    private TextField password;

    protected String getName() {
        return name.getText();
    }

    protected String getPassword() {
        return password.getText();
    }

    @FXML
    protected abstract void login(ActionEvent event);
}
