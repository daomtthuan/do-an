package controller.customer;

import controller.Controller;
import controller.ControllerChangePassword;
import controller.ControllerFood;
import controller.ControllerInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import library.ErrorAlert;
import main.DialogSecondaryStage;
import main.SecondaryStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller order.
 */
public final class ControllerOrder implements Controller, Initializable {
    @FXML
    private AnchorPane viewFood;
    @FXML
    private AnchorPane viewBill;
    @FXML
    private Button discountButton;
    @FXML
    private Button informationButton;
    @FXML
    private Button accountButton;

    private void setup() {
        if (SecondaryStage.getInstance().getAccount() == null) {
            informationButton.setText("Register");
            informationButton.setOnAction(event -> {
                DialogSecondaryStage.getInstance().setScene("/view/ViewInformation.fxml", new ControllerRegister());
                DialogSecondaryStage.getInstance().getStage().show();
            });

            accountButton.setText("Login");
            accountButton.setOnAction(event -> {
                DialogSecondaryStage.getInstance().setScene("/view/ViewLogin.fxml", new ControllerCustomerLogin());
                DialogSecondaryStage.getInstance().getStage().show();
            });

        } else {
            informationButton.setText("Customer Information");
            informationButton.setOnAction(event -> {
                ControllerInformation controllerInformation = new ControllerInformation();
                controllerInformation.setAccount(SecondaryStage.getInstance().getAccount());
                DialogSecondaryStage.getInstance().setScene("/view/ViewInformation.fxml", controllerInformation);
                DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                    windowEvent.consume();
                    DialogSecondaryStage.getInstance().getStage().hide();
                    SecondaryStage.getInstance().getStage().show();
                });
                DialogSecondaryStage.getInstance().getStage().show();
                SecondaryStage.getInstance().getStage().hide();
            });

            accountButton.setText("Change Password");
            accountButton.setOnAction(event -> {
                DialogSecondaryStage.getInstance().setScene("/view/ViewChangePassword.fxml", new ControllerChangePassword(SecondaryStage.getInstance().getAccount()));
                DialogSecondaryStage.getInstance().getStage().show();
                DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                    windowEvent.consume();
                    DialogSecondaryStage.getInstance().getStage().hide();
                    SecondaryStage.getInstance().getStage().show();
                });
                SecondaryStage.getInstance().getStage().hide();
            });
        }

        if (SecondaryStage.getInstance().getDiscount() == null) {
            discountButton.setText("Enter Discount Code");
        } else {
            discountButton.setText("Change Discount Code");
        }

        discountButton.setOnAction(event -> {
            DialogSecondaryStage.getInstance().setScene("/view/customer/ViewEnterDiscount.fxml");
            DialogSecondaryStage.getInstance().getStage().show();
            SecondaryStage.getInstance().getStage().hide();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
        SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> setup());
        // Event On Close stage
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            SecondaryStage.getInstance().setScene("/view/customer/ViewWelcome.fxml");
            SecondaryStage.getInstance().getStage().show();
            SecondaryStage.getInstance().setAccount(null);
            SecondaryStage.getInstance().setDiscount(null);
            SecondaryStage.getInstance().setOrdering(false);
        });

        // Load view Food into AnchorPane viewFood
        try {
            ControllerFood controllerFood = new ControllerFood();
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewFood.fxml"));
            view.setController(controllerFood);
            viewFood.getChildren().add(view.load());
        } catch (IOException e) {
            ErrorAlert.getInstance().showAndWait(e);
        }
    }
}
