package controller.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.DialogStage;
import main.SecondaryStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller order.
 */
public final class ControllerOrder implements Initializable {
    @FXML
    private VBox viewFood;

    @FXML
    private VBox viewBill;

    @FXML
    private Button information;

    @FXML
    private Button account;

    @FXML
    private void register() {

        if (SecondaryStage.getInstance().getAccount() == null) {
            try {
                FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
                view.setController(new ControllerRegister());
                DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
                DialogStage.getInstance().getStage().show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void setup() {
        if (SecondaryStage.getInstance().getAccount() == null) {
            information.setText("Register");
            account.setText("Login");
        } else {
            information.setText("Customer Information");
            account.setText("Logout");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
        SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> setup());
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {

        });
    }
}
