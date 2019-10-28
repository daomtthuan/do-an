package controller;

import api.ApiAccount;
import app.PrimaryStage;
import app.SecondaryStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import plugin.alert.AlertWarning;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Controller, Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private Button submit;

    protected String getName() {
        return name.getText();
    }

    protected String getPassword() {
        return password.getText();
    }

    protected Button getSubmit() {
        return submit;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit.setDefaultButton(true);
        submit.setOnAction(actionEvent -> {
            // Check validate input account name and password
            if (getName().matches("^\\w{2,50}$") && getPassword().matches("^\\w{1,50}$")) {
                Account account = ApiAccount.getInstance().login("Admin", getName(), getPassword());
                // If login success
                if (account != null) {
                    // Setup primary stage for employee
                    PrimaryStage.getInstance().setScene("/view/employee/ViewEmployee.fxml");
                    PrimaryStage.getInstance().setAccount(account);

                    // Setup secondary stage for customer and show it
                    SecondaryStage.getInstance().setScene("/view/customer/ViewWelcome.fxml");
                    SecondaryStage.getInstance().getStage().show();
                    SecondaryStage.getInstance().setAccount(null);
                } else {
                    AlertWarning.getInstance().showAndWait("Fail!", "Account or password is incorrect.\nPlease check again.");
                }
            } else {
                AlertWarning.getInstance().showAndWait("Fail!", "Invalid account.\nPlease check again.");
            }
        });
    }
}
