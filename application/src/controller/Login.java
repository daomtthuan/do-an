package controller;

import javafx.fxml.FXML;

import java.awt.event.ActionEvent;

public class Login {

    @FXML
    private String account;

    @FXML
    private String password;

    public void show(ActionEvent event) {
        System.out.println(account +" " + password);
    }
}
