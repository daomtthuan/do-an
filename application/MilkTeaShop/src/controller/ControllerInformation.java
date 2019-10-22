package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.DialogStage;
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
     * Gets female.
     *
     * @return the female
     */
    protected boolean getFemale() {
        return female.isSelected();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle("ACCOUNT INFORMATION");

        name.setText(account.getName());
        male.setSelected(account.isMale());
        female.setSelected(account.isFemMale());
        String[] elements = account.getBirthday().split("-");
        birthday.setValue(LocalDate.of(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2])));
        address.setText(account.getAddress());
        phone.setText(account.getPhone());
        email.setText(account.getEmail());

        button.setText("Edit Information");
        button.setOnAction(event -> {

        });
    }
}
