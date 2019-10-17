package controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import ui.ErrorAlert;
import ui.WarningAlert;

import java.io.IOException;

public class Login extends controller.Login {

    @Override
    public void login(ActionEvent event) {
        Account account = access.Account.getInstance().login(getName(), getPassword());
        if (account == null) {
            WarningAlert.getInstance().showAndWait("Login failed");
        } else {
            try {
                // Create stageCustomer with Scene viewWelcome
                Stage secondaryStage = new Stage();
                FXMLLoader view = new FXMLLoader(this.getClass().getResource("/view/customer/Welcome.fxml"));
                secondaryStage.setTitle("Milk Tea Shop");
                secondaryStage.setScene(new Scene(view.load()));
                secondaryStage.setResizable(false);
                secondaryStage.show();


                //Change Scene viewEmployee on stageEmployee
                Stage stageEmployee = (Stage) (((Node) event.getSource()).getScene().getWindow());
            } catch (IOException e) {
                ErrorAlert.getInstance().showAndWait(e);
            }
        }
    }
}
