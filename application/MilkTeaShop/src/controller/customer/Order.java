package controller.customer;

import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import controller.general.ChangePassword;
import controller.Controller;
import controller.general.Account;
import controller.general.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public final class Order implements Controller, Initializable {
    @FXML
    private AnchorPane menuComponent;
    @FXML
    private AnchorPane billComponent;
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
                DialogSecondaryStage.getInstance().setScene("/view/general/Account.fxml", new Register());
                DialogSecondaryStage.getInstance().show();
            });

            accountButton.setText("Login");
            accountButton.setOnAction(event -> {
                DialogSecondaryStage.getInstance().setScene("/view/general/Login.fxml", new Login());
                DialogSecondaryStage.getInstance().show();
            });

        } else {
            informationButton.setText("Account");
            informationButton.setOnAction(event -> {
                Account controller = new Account();
                controller.setAccount(SecondaryStage.getInstance().getAccount());
                DialogSecondaryStage.getInstance().setScene("/view/general/Account.fxml", "/style/general/Account.css", controller);
                DialogSecondaryStage.getInstance().show();
                SecondaryStage.getInstance().getStage().hide();
            });

            accountButton.setText("Change Password");
            accountButton.setOnAction(event -> {
                DialogSecondaryStage.getInstance().setScene("/view/general/ChangePassword.fxml", new ChangePassword(SecondaryStage.getInstance().getAccount()));
                DialogSecondaryStage.getInstance().show();
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
            DialogSecondaryStage.getInstance().show();
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

        menuComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/Menu.fxml", new Menu()));
    }
}
