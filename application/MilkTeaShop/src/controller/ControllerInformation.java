package controller;

import api.ApiAccount;
import app.PrimaryStage;
import app.SecondaryStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Account;
import plugin.StringTool;
import plugin.alert.AlertInformation;
import plugin.alert.AlertWarning;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerInformation implements Controller, Initializable {
    @FXML
    private Button button;
    @FXML
    private Label title;
    @FXML
    private TextField name;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    protected Button getButton() {
        return button;
    }

    protected void setTitle(String title) {
        this.title.setText(title);
    }

    protected String getName() {
        return name.getText();
    }

    protected boolean getMale() {
        return male.isSelected();
    }

    protected String getBirthday() {
        return birthday.getValue() == null ? "" : birthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    protected String getAddress() {
        return address.getText();
    }

    protected String getPhone() {
        return phone.getText();
    }

    protected String getEmail() {
        return email.getText();
    }

    private void edit() {
        setTitle("EDIT INFORMATION");
        button.setText("Submit");
        name.setDisable(false);
        male.setDisable(false);
        female.setDisable(false);
        birthday.setDisable(false);
        address.setDisable(false);
        phone.setDisable(false);
        email.setDisable(false);
        name.setStyle("-fx-opacity: 100");
        male.setStyle("-fx-opacity: 100");
        female.setStyle("-fx-opacity: 100");
        birthday.setStyle("-fx-opacity: 100");
        birthday.getEditor().setStyle("-fx-opacity: 100");
        address.setStyle("-fx-opacity: 100");
        phone.setStyle("-fx-opacity: 100");
        email.setStyle("-fx-opacity: 100");

        // setup event for button
        button.setOnAction(event -> {
            String name = StringTool.fixString(getName());
            boolean gender = getMale();
            String birthday = getBirthday();
            String address = StringTool.fixString(getAddress());
            String phone = getPhone();
            String email = getEmail().toLowerCase();
            if (name.matches("^[a-zA-Z ]{1,50}$") &&
                    address.matches("^.{1,100}$") &&
                    phone.matches("^(([+]{1}[0-9]{2}|0)[0-9]{9,12})$") &&
                    email.matches("^([a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,})?$")) {
                Account account = ApiAccount.getInstance().update(this.account.getId(), name, gender, birthday, address, phone, email);

                if (account != null) {
                    this.account = account;
                    if (account.getRoll() == 1) {
                        SecondaryStage.getInstance().setAccount(account);
                    } else {
                        PrimaryStage.getInstance().setAccount(account);
                    }
                    AlertInformation.getInstance().showAndWait("Success!", "Your Information Account has been edited.");
                    view();
                } else {
                    AlertWarning.getInstance().showAndWait("Fail!", "Can not edit Account Information.\nPlease notify staff.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
            }
        });
    }

    private void view() {
        setTitle("ACCOUNT INFORMATION");
        button.setText("Edit Information");
        name.setDisable(true);
        male.setDisable(true);
        female.setDisable(true);
        birthday.setDisable(true);
        address.setDisable(true);
        phone.setDisable(true);
        email.setDisable(true);
        name.setStyle("-fx-opacity: 1");
        male.setStyle("-fx-opacity: 1");
        female.setStyle("-fx-opacity: 1");
        birthday.setStyle("-fx-opacity: 1");
        birthday.getEditor().setStyle("-fx-opacity: 1");
        address.setStyle("-fx-opacity: 1");
        phone.setStyle("-fx-opacity: 1");
        email.setStyle("-fx-opacity: 1");
        button.setOnAction(event -> edit());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(account.getName());
        male.setSelected(account.isMale());
        female.setSelected(account.isFemMale());
        String[] elements = account.getBirthday().split("-");
        birthday.setValue(LocalDate.of(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2])));
        address.setText(account.getAddress());
        phone.setText(account.getPhone());
        email.setText(account.getEmail());

        // mode view
        view();
    }
}
