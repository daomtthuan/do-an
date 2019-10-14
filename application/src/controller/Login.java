package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField account;

    @FXML
    private TextField password;

    @FXML
    private Button button;

    @FXML
    public void show(ActionEvent event) {
        System.out.println(account.getText() +" " + password.getText());
    }
}
