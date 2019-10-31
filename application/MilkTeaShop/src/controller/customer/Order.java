package controller.customer;

import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.ChangePassword;
import controller.Controller;
import controller.Information;
import controller.ViewFood;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import app.alert.AlertError;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class Order implements Controller, Initializable {
    @FXML
    private AnchorPane foodAnchorPane;
    @FXML
    private AnchorPane billAnchorPane;
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
                DialogSecondaryStage.getInstance().setScene("/view/Information.fxml", new Register());
                DialogSecondaryStage.getInstance().getStage().show();
            });

            accountButton.setText("Login");
            accountButton.setOnAction(event -> {
                DialogSecondaryStage.getInstance().setScene("/view/Login.fxml", new CustomerLogin());
                DialogSecondaryStage.getInstance().getStage().show();
            });

        } else {
            informationButton.setText("Customer Information");
            informationButton.setOnAction(event -> {
                Information controller = new Information();
                controller.setAccount(SecondaryStage.getInstance().getAccount());
                DialogSecondaryStage.getInstance().setScene("/view/Information.fxml", controller);
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
                DialogSecondaryStage.getInstance().setScene("/view/ChangePassword.fxml", new ChangePassword(SecondaryStage.getInstance().getAccount()));
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
            DialogSecondaryStage.getInstance().setScene("/view/customer/EnterDiscount.fxml");
            DialogSecondaryStage.getInstance().getStage().show();
            SecondaryStage.getInstance().getStage().hide();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
        SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> setup());
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css");
            SecondaryStage.getInstance().getStage().show();
            SecondaryStage.getInstance().setAccount(null);
            SecondaryStage.getInstance().setDiscount(null);
            SecondaryStage.getInstance().setOrdering(false);
        });

        try {
            ViewFood controller = new ViewFood();
            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewFood.fxml"));
            view.setController(controller);
            foodAnchorPane.getChildren().add(view.load());
        } catch (IOException e) {
            AlertError.getInstance().showAndWait(e);
        }
    }
}
