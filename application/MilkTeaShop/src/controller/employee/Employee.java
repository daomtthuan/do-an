package controller.employee;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import app.primary.PrimarySubDialog;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import component.controller.employee.ManageAccountPane;
import component.controller.employee.ManageMenuPane;
import component.controller.employee.ManageShopPane;
import controller.general.ChangePassword;
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

	@FXML
	private void changePassword() {
		PrimarySubDialog.getInstance().setScene("/view/general/ChangePassword.fxml", new ChangePassword(PrimaryStage.getInstance().getAccount()));
		PrimarySubDialog.getInstance().getStage().show();
		PrimaryStage.getInstance().getStage().hide();
	}

	@FXML
	private void openShop() {
		SecondaryStage.getInstance().getStage().setMaximized(true);
		SecondaryStage.getInstance().setX(SecondaryStage.getInstance().getStage().getX());
		SecondaryStage.getInstance().setY(SecondaryStage.getInstance().getStage().getY());
		SecondaryStage.getInstance().setWidth(SecondaryStage.getInstance().getStage().getWidth());
		SecondaryStage.getInstance().setHeight(SecondaryStage.getInstance().getStage().getHeight());
		SecondaryDialog.getInstance().setX(SecondaryStage.getInstance().getStage().getX());
		SecondaryDialog.getInstance().setY(SecondaryStage.getInstance().getStage().getY());
		SecondaryDialog.getInstance().setWidth(SecondaryStage.getInstance().getStage().getWidth());
		SecondaryDialog.getInstance().setHeight(SecondaryStage.getInstance().getStage().getHeight());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageShop();
	}
}
