package controller.customer;

import controller.ControllerChangePassword;
import controller.ControllerInformation;
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

    private void setup() {
        if (SecondaryStage.getInstance().getAccount() == null) {
            information.setText("Register");
            information.setOnAction(event -> {
                try {
                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
                    view.setController(new ControllerRegister());
                    DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogStage.getInstance().getStage().show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            account.setText("Login");
            account.setOnAction(event -> {
                try {
                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewLogin.fxml"));
                    view.setController(new ControllerCustomerLogin());
                    DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogStage.getInstance().getStage().show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } else {
            information.setText("Customer Information");
            information.setOnAction(event -> {
                try {
                    ControllerInformation controllerInformation = new ControllerInformation();
                    controllerInformation.setAccount(SecondaryStage.getInstance().getAccount());

                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
                    view.setController(controllerInformation);
                    DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                        windowEvent.consume();
                        DialogStage.getInstance().getStage().hide();
                        SecondaryStage.getInstance().getStage().show();
                    });
                    DialogStage.getInstance().getStage().show();
                    SecondaryStage.getInstance().getStage().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            account.setText("Change Password");
            account.setOnAction(event -> {
                try {
                    ControllerChangePassword controllerChangePassword = new ControllerChangePassword();
                    controllerChangePassword.setAccount(SecondaryStage.getInstance().getAccount());

                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewChangePassword.fxml"));
                    view.setController(controllerChangePassword);
                    DialogStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogStage.getInstance().getStage().show();
                    SecondaryStage.getInstance().getStage().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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
