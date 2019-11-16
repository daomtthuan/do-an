package controller.manager;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.manager.ManageAccountPane;
import component.controller.manager.ManageMenuPane;
import component.controller.manager.ManageShopPane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Manager implements Controller {
	@FXML
	private VBox manageComponent;

	@FXML
	private void manageIncome() {

	}

	@FXML
	private void manageShop() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/ManageShopPane.fxml", new ManageShopPane()));
	}

	@FXML
	private void manageMenu() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/ManageMenuPane.fxml", new ManageMenuPane()));
	}

	@FXML
	private void manageAccount() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/manager/ManageAccountPane.fxml", new ManageAccountPane()));
	}
}
