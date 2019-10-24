package controller;

import access.AccessAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import library.Tool;
import library.WarningAlert;
import main.SecondaryStage;
import model.Account;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * The type Controller information.
 */
public class ControllerInformation implements Initializable {
    private Account account;
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

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets button.
     *
     * @return the button
     */
    protected Button getButton() {
        return button;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    protected void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    protected String getName() {
        return name.getText();
    }

    /**
     * Gets male.
     *
     * @return the male
     */
    protected boolean getMale() {
        return male.isSelected();
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    protected String getBirthday() {
        return birthday.getValue() == null ? "" : birthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    protected String getAddress() {
        return address.getText();
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    protected String getPhone() {
        return phone.getText();
    }

    /**
     * Gets email.
     *
     * @return the email
     */
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
        button.setOnAction(event -> {
            String name = Tool.fixString(getName());
            boolean gender = getMale();
            String birthday = getBirthday();
            String address = Tool.fixString(getAddress());
            String phone = getPhone();
            String email = getEmail().toLowerCase();
            if (name.matches("^[a-zA-Z ]{1,50}$") &&
                    address.matches("^.{1,100}$") &&
                    phone.matches("^(([+]{1}[0-9]{2}|0)[0-9]{9,12})$") &&
                    email.matches("^([a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,})?$")) {
                Account account = AccessAccount.getInstance().update(this.account.getId(), name, gender, birthday, address, phone, email);

                if (account != null) {
                    this.account = account;
                    SecondaryStage.getInstance().setAccount(account);
                    view();
                } else {
                    WarningAlert.getInstance().showAndWait("Edit Information failed!", "Can not edit Account Information.\nPlease notify staff.");
                }
            } else {
                WarningAlert.getInstance().showAndWait("Edit Information failed!", "Invalid information.\nPlease check again.");
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

        view();
    }
}
