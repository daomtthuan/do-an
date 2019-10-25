package controller.customer;

import access.AccessAccount;
import controller.ControllerInformation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import library.ErrorAlert;
import library.InformationAlert;
import library.Tool;
import library.WarningAlert;
import main.DialogSecondaryStage;
import main.SecondaryStage;
import model.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Controller register.
 */
public final class ControllerRegister extends ControllerInformation {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle("REGISTER");
        SecondaryStage.getInstance().getStage().hide();
        DialogSecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogSecondaryStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });

        // setup event for button
        getButton().setOnAction(event -> {
            // Get value from input
            StringBuilder account = new StringBuilder();
            String name = Tool.fixString(getName());
            boolean gender = getMale();
            String birthday = getBirthday();
            String address = Tool.fixString(getAddress());
            String phone = getPhone();
            String email = getEmail().toLowerCase();

            // Check valid input
            if (name.matches("^[a-zA-Z ]{1,50}$") &&
                    address.matches("^.{1,100}$") &&
                    phone.matches("^(([+]{1}[0-9]{2}|0)[0-9]{9,12})$") &&
                    email.matches("^([a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,})?$")) {

                // create account name
                for (String s : name.split(" ")) {
                    account.append(String.valueOf(s.charAt(0)).toLowerCase());
                }

                // Insert account into database
                Account newAccount = AccessAccount.getInstance().insert(account.toString(), 1, name, gender, birthday, address, phone, email);

                // Check insert success
                if (newAccount != null) {
                    // Set up view ControllerOrder for customer on secondary Stage
                    try {
                        SecondaryStage.getInstance().setAccount(newAccount);

                        // if customer is ordering, back to order view, otherwise create view order
                        if (!SecondaryStage.getInstance().isOrdering()) {
                            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewOrder.fxml"));
                            SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                        }

                        // Alert insert success
                        InformationAlert.getInstance().showAndWait("Success!",
                                "Your Customer Account: " + newAccount.getAccount() + "\nYour default Password: 1\nPlease change your Password at next Login times.\n" +
                                        "Thanks for coming to our shop!");

                        // Show stage order
                        DialogSecondaryStage.getInstance().getStage().hide();
                        SecondaryStage.getInstance().getStage().show();
                    } catch (IOException e) {
                        ErrorAlert.getInstance().showAndWait(e);
                    }
                } else {
                    WarningAlert.getInstance().showAndWait("Fail!", "Can not register.\nPlease notify staff.");
                }
            } else {
                WarningAlert.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
            }
        });
    }
}
