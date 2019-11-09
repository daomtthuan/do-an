package controller.general;

import app.pattern.Controller;
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
    private Label nameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;

    private Account account;

    @Contract(pure = true)
    public WatchAccount(@NotNull Account account) {
        this.account = account;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(account.getName());
        genderLabel.setText(account.isMale() ? "Male" : "Female");
        birthdayLabel.setText(account.getBirthday());
        addressLabel.setText(account.getAddress());
        phoneLabel.setText(account.getPhone());
        emailLabel.setText(account.getEmail());
    }
}
