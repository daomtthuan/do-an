package controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.DialogStage;
import main.SecondaryStage;
import ui.ErrorAlert;

import java.io.IOException;

public final class Welcome {

    @FXML
    private void login(ActionEvent event) {

    }

    @FXML
    private void register(ActionEvent event) {
        try {
            // Set up view Register for customer on dialog Stage and show it
            FXMLLoader view = new FXMLLoader(this.getClass().getResource("/view/Information.fxml"));
            view.setController(new Register());
            DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
            DialogStage.getInstance().getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void order(ActionEvent event) {
        try {
            // Set up view Order for customer on secondary Stage
            FXMLLoader view = new FXMLLoader(this.getClass().getResource("/view/customer/Order.fxml"));
            SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }
}
