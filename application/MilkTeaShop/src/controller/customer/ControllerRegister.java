package controller.customer;

import api.ApiAccount;
import app.DialogSecondaryStage;
import app.SecondaryStage;
import controller.Controller;
import controller.ControllerInformation;
import model.Account;
import plugin.StringTool;
import plugin.alert.AlertInformation;
import plugin.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public final class ControllerRegister extends ControllerInformation implements Controller {
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
            String name = StringTool.fixString(getName());
            boolean gender = getMale();
            String birthday = getBirthday();
            String address = StringTool.fixString(getAddress());
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
                Account newAccount = ApiAccount.getInstance().insert(account.toString(), 1, name, gender, birthday, address, phone, email);

                // Check insert success
                if (newAccount != null) {
                    // Set up view ControllerOrder for customer on secondary Stage
                    SecondaryStage.getInstance().setAccount(newAccount);

                    // if customer is ordering, back to order view, otherwise create view order
                    if (!SecondaryStage.getInstance().isOrdering()) {
                        SecondaryStage.getInstance().setScene("/view/customer/ViewOrder.fxml");
                    }

                    // Alert insert success
                    AlertInformation.getInstance().showAndWait("Success!",
                            "Your Customer Account: " + newAccount.getAccount() + "\nYour default Password: 1\nPlease change your Password at next Login times.\n" +
                                    "Thanks for coming to our shop!");

                    // Show stage order
                    DialogSecondaryStage.getInstance().getStage().hide();
                    SecondaryStage.getInstance().getStage().show();
                } else {
                    AlertWarning.getInstance().showAndWait("Fail!", "Can not register.\nPlease notify staff.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Invalid information.\nPlease check again.");
            }
        });
    }
}
