package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Abstract controller for view Login.
 */
public abstract class Login {
    @FXML
    private TextField name;
    @FXML
    private TextField password;

    /**
     * Gets name.
     *
     * @return the name
     */
    protected String getName() {
        return name.getText();
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    protected String getPassword() {
        return password.getText();
    }

    /**
     * Login. Event click button Login
     *
     * @param event the event
     */
    @FXML
    protected abstract void login(ActionEvent event);
}
