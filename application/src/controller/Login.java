package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    public void show(ActionEvent event){
        String ac = account.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Welcome "+ac+" !");
        alert.show();
    }
}
