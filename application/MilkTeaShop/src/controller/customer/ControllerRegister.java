package controller.customer;

import access.AccessAccount;
import controller.ControllerInformation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import library.ErrorAlert;
import library.Tool;
import library.WarningAlert;
import main.DialogStage;
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
        DialogStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            DialogStage.getInstance().getStage().hide();
            SecondaryStage.getInstance().getStage().show();
        });

        getButton().setOnAction(event -> {
            StringBuilder account = new StringBuilder();
            String name = Tool.fixString(getName());
            boolean gender = getMale();
            String birthday = getBirthday();
            String address = Tool.fixString(getAddress());
            String phone = getPhone();
            String email = getEmail().toLowerCase();
            if (name.matches("^[a-zA-Z ]{1,50}$") &&
                    address.matches("^.{1,100}$") &&
                    phone.matches("^(([+]{1}[0-9]{2}|0)[0-9]{9,12})$") &&
                    email.matches("^([a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,})?$")) {

                for (String s : name.split(" ")) {
                    account.append(String.valueOf(s.charAt(0)).toLowerCase());
                }

                Account newAccount = AccessAccount.getInstance().insert(account.toString(), 1, name, gender, birthday, address, phone, email);

                if (newAccount != null) {
                    SecondaryStage.getInstance().setAccount(newAccount);
                    DialogStage.getInstance().getStage().hide();
                    try {
                        // Set up view ControllerOrder for customer on secondary Stage
                        if (!SecondaryStage.getInstance().isOrdering()) {
                            FXMLLoader view = new FXMLLoader(getClass().getResource("/view/customer/ViewOrder.fxml"));
                            SecondaryStage.getInstance().getStage().setScene(new Scene(view.load()));
                        }
                        DialogStage.getInstance().getStage().hide();
                        SecondaryStage.getInstance().getStage().show();
                    } catch (IOException e) {
                        ErrorAlert.getInstance().showAndWait(e);
                    }
                } else {
                    WarningAlert.getInstance().showAndWait("Register failed", "Can not register.\nPlease notify staff.");
                }
            } else {
                WarningAlert.getInstance().showAndWait("Register failed", "Invalid information.\nPlease check again.");
            }
        });
    }
}
