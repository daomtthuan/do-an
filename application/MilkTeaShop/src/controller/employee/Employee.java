package controller.employee;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import app.primary.PrimarySubDialog;
import component.controller.employee.ManageAccountPane;
import component.controller.employee.ManageMenuPane;
import component.controller.employee.ManageShopPane;
import controller.general.WatchAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Employee implements Controller, Initializable {
	@FXML
	private VBox manageComponent;

	@FXML
	private void manageShop() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/ManageShopPane.fxml", new ManageShopPane()));
	}

	@FXML
	private void manageMenu() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/ManageMenuPane.fxml", new ManageMenuPane()));
	}

	@FXML
	private void manageAccount() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/employee/ManageAccountPane.fxml", new ManageAccountPane()));
	}

	@FXML
	private void watchAccount() {
		PrimarySubDialog.getInstance().setScene("/view/general/WatchAccount.fxml", "/style/general/Account.css", new WatchAccount(PrimaryStage.getInstance().getAccount()));
		PrimarySubDialog.getInstance().getStage().show();
		PrimaryStage.getInstance().getStage().hide();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageShop();
	}
}
