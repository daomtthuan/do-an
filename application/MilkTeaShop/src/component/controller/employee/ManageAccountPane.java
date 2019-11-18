package component.controller.employee;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.general.managepane.ManageCustomerPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageAccountPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageCustomerPane()));
	}
}
