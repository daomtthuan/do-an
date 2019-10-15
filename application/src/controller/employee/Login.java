package controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends controller.global.Login {

    @FXML
    @Override
    public void login(ActionEvent event) throws IOException {
        // Create stageCustomer with Scene viewWelcome
        Stage stageCustomer = new Stage();
        FXMLLoader viewCustomerWelcome = new FXMLLoader(this.getClass().getResource("/view/customer/Welcome.fxml"));
        stageCustomer.setScene(new Scene(viewCustomerWelcome.load()));
        stageCustomer.setResizable(false);
        stageCustomer.show();

        // Change Scene viewEmployee on stageEmployee
        //   Stage stageEmployee = (Stage) (((Node) event.getSource()).getScene().getWindow());
    }
}
