package controller.general;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Account;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class WatchAccount implements Controller, Initializable {
    @FXML
    private Label name;
    @FXML
    private Label gender;
    @FXML
    private Label birthday;
    @FXML
    private Label address;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    private Account account;

    @Contract(pure = true)
    public WatchAccount(@NotNull Account account) {
        this.account = account;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(account.getName());
        gender.setText(account.isMale() ? "Male" : "Female");
        birthday.setText(account.getBirthday());
        address.setText(account.getAddress());
        phone.setText(account.getPhone());
        email.setText(account.getEmail());
    }
}
