package controller.general;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Account;
import org.jetbrains.annotations.NotNull;

public final class WatchAccount implements Controller {
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

    public WatchAccount(@NotNull Account account) {
        name.setText(account.getName());
        gender.setText(account.isMale() ? "Male" : "Female");
        birthday.setText(account.getBirthday());
        address.setText(account.getAddress());
        phone.setText(account.getPhone());
        email.setText(account.getEmail());
    }
}
