package component.controller.manager;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.general.managepane.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageAccountPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@FXML
	private void manageCustomer() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageCustomerPane()));
	}

	@FXML
	private void manageEmployee() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageEmployeePane()));
	}

	@FXML
	private void manageManager() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageManagerPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageCustomer();
	}
}
