package controller.manager;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import app.primary.PrimarySubDialog;
import component.controller.manager.ManageAccountPane;
import component.controller.manager.ManageIncomePane;
import component.controller.manager.ManageMenuPane;
import component.controller.manager.ManageShopPane;
import controller.general.ChangePassword;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Manager implements Controller, Initializable {
	@FXML
	private VBox manageComponent;

	@FXML
	private void manageIncome() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/ManageIncomePane.fxml", new ManageIncomePane()));
	}

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
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/ManageAccountPane.fxml", new ManageAccountPane()));
	}

	@FXML
	private void changePassword() {
		PrimarySubDialog.getInstance().setScene("/view/general/ChangePassword.fxml", new ChangePassword(PrimaryStage.getInstance().getAccount()));
		PrimarySubDialog.getInstance().getStage().show();
		PrimaryStage.getInstance().getStage().hide();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageIncome();
	}
}
