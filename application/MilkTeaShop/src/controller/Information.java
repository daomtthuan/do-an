package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract controller for view Information.
 */
public abstract class Information {
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
     * Submit. Event click button Submit
     *
     * @param event the event
     */
    @FXML
    protected abstract void submit(ActionEvent event);

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
        return birthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
}
