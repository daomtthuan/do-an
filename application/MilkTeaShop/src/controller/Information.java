package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Abstract controller for view Information.
 */
public abstract class Information {
    @FXML
    private Label title;

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
}
