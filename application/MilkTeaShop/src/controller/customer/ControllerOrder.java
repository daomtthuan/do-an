package controller.customer;

import access.AccessCategory;
import controller.ControllerChangePassword;
import controller.ControllerInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.DialogSecondaryStage;
import main.SecondaryStage;
import model.Category;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Controller order.
 */
public final class ControllerOrder implements Initializable {
    private ArrayList<Category> categories;

    @FXML
    private VBox viewFood;
    @FXML
    private VBox viewBill;
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
                try {
                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
                    view.setController(new ControllerRegister());
                    DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogSecondaryStage.getInstance().getStage().show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            accountButton.setText("Login");
            accountButton.setOnAction(event -> {
                try {
                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewLogin.fxml"));
                    view.setController(new ControllerCustomerLogin());
                    DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogSecondaryStage.getInstance().getStage().show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } else {
            informationButton.setText("Customer Information");
            informationButton.setOnAction(event -> {
                try {
                    ControllerInformation controllerInformation = new ControllerInformation();
                    controllerInformation.setAccount(SecondaryStage.getInstance().getAccount());

                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewInformation.fxml"));
                    view.setController(controllerInformation);
                    DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                        windowEvent.consume();
                        DialogSecondaryStage.getInstance().getStage().hide();
                        SecondaryStage.getInstance().getStage().show();
                    });
                    DialogSecondaryStage.getInstance().getStage().show();
                    SecondaryStage.getInstance().getStage().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            accountButton.setText("Change Password");
            accountButton.setOnAction(event -> {
                try {
                    FXMLLoader view = new FXMLLoader(getClass().getResource("/view/ViewChangePassword.fxml"));
                    view.setController(new ControllerChangePassword(SecondaryStage.getInstance().getAccount()));
                    DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                    DialogSecondaryStage.getInstance().getStage().show();
                    DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
                        windowEvent.consume();
                        DialogSecondaryStage.getInstance().getStage().hide();
                        SecondaryStage.getInstance().getStage().show();
                    });
                    SecondaryStage.getInstance().getStage().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (SecondaryStage.getInstance().getDiscount() == null) {
            discountButton.setText("Enter Discount Code");
        } else {
            discountButton.setText("Change Discount Code");
        }

        discountButton.setOnAction(event -> {
            try {
                FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewEnterDiscount.fxml"));
                DialogSecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                DialogSecondaryStage.getInstance().getStage().show();
                SecondaryStage.getInstance().getStage().hide();


            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
        SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> setup());
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewWelcome.fxml"));
                SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                SecondaryStage.getInstance().getStage().show();
                SecondaryStage.getInstance().setAccount(null);
                SecondaryStage.getInstance().setDiscount(null);
                SecondaryStage.getInstance().setOrdering(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        categories = AccessCategory.getInstance().load();
        categories.forEach(category -> {
            System.out.println(category.getName());
        });
    }
}
