package controller;

import api.AccountApi;
import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.stage.PrimaryStage;
import app.stage.SecondaryStage;
import app.string.Regex;
import app.string.Tool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Account;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Information implements Controller, Initializable {
    @FXML
    private Button defaultButton;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    private Account account;

    private int getId() {
        return account.getId();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    protected void setTitle(String titleLabel) {
        this.titleLabel.setText(titleLabel);
    }

    protected void setDefaultButton(String defaultButton) { this.defaultButton.setText(defaultButton);}

    protected Button getDefaultButton() {
        return defaultButton;
    }

    protected String getName() {
        return nameTextField.getText();
    }

    protected boolean isMale() {
        return maleRadioButton.isSelected();
    }

    protected String getBirthday() {
        return birthdayDatePicker.getValue() == null ? "" : birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    protected String getAddress() {
        return addressTextField.getText();
    }

    protected String getPhone() {
        return phoneTextField.getText();
    }

    protected String getEmail() {
        return emailTextField.getText();
    }

    private void edit() {
        setTitle("EDIT INFORMATION");
        setDefaultButton("Submit");
        nameTextField.setDisable(false);
        maleRadioButton.setDisable(false);
        femaleRadioButton.setDisable(false);
        birthdayDatePicker.setDisable(false);
        addressTextField.setDisable(false);
        phoneTextField.setDisable(false);
        emailTextField.setDisable(false);
        nameTextField.setStyle("-fx-opacity: 100");
        maleRadioButton.setStyle("-fx-opacity: 100");
        femaleRadioButton.setStyle("-fx-opacity: 100");
        birthdayDatePicker.setStyle("-fx-opacity: 100");
        birthdayDatePicker.getEditor().setStyle("-fx-opacity: 100");
        addressTextField.setStyle("-fx-opacity: 100");
        phoneTextField.setStyle("-fx-opacity: 100");
        emailTextField.setStyle("-fx-opacity: 100");

        defaultButton.setOnAction(event -> {
            String name = Tool.fixString(getName());
            boolean gender = isMale();
            String birthday = getBirthday();
            String address = Tool.fixString(getAddress());
            String phone = getPhone();
            String email = getEmail().toLowerCase();
            if (name.matches(Regex.NAME) && address.matches(Regex.ADDRESS) && phone.matches(Regex.PHONE) && email.matches(Regex.EMAIL)) {
                Account account = AccountApi.getInstance().update(getId(), name, gender, birthday, address, phone, email);

                if (account != null) {
                    setAccount(account);
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
        setDefaultButton("Edit Information");
        nameTextField.setDisable(true);
        maleRadioButton.setDisable(true);
        femaleRadioButton.setDisable(true);
        birthdayDatePicker.setDisable(true);
        addressTextField.setDisable(true);
        phoneTextField.setDisable(true);
        emailTextField.setDisable(true);
        nameTextField.setStyle("-fx-opacity: 1");
        maleRadioButton.setStyle("-fx-opacity: 1");
        femaleRadioButton.setStyle("-fx-opacity: 1");
        birthdayDatePicker.setStyle("-fx-opacity: 1");
        birthdayDatePicker.getEditor().setStyle("-fx-opacity: 1");
        addressTextField.setStyle("-fx-opacity: 1");
        phoneTextField.setStyle("-fx-opacity: 1");
        emailTextField.setStyle("-fx-opacity: 1");
        defaultButton.setOnAction(event -> edit());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.setText(account.getName());
        maleRadioButton.setSelected(account.isMale());
        femaleRadioButton.setSelected(account.isFemMale());
        String[] elements = account.getBirthday().split("-");
        birthdayDatePicker.setValue(LocalDate.of(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2])));
        addressTextField.setText(account.getAddress());
        phoneTextField.setText(account.getPhone());
        emailTextField.setText(account.getEmail());
        getDefaultButton().setDefaultButton(true);
        view();
    }
}
