package controller.manager;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.ManageAccountPane;
import component.controller.ManageMenuPane;
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

	}

	@FXML
	private void manageMenu() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManageMenuPane.fxml", new ManageMenuPane()));
	}

	@FXML
	private void manageAccount() {
		manageComponent.getChildren().clear();
		manageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManageAccountPane.fxml", new ManageAccountPane()));
	}

//	@FXML
//	private VBox incomeComponent;
//	@FXML
//	private VBox billComponent;
//	@FXML
//	private VBox tableComponent;
//	@FXML
//	private VBox categoryComponent;
//	@FXML
//	private VBox foodComponent;
//	@FXML
//	private VBox discountComponent;
//	@FXML
//	private VBox customerComponent;
//	@FXML
//	private VBox employeeComponent;
//	@FXML
//	private VBox managerComponent;
//	private ManageIncome manageIncome;
//	private ManageBill manageBill;
//	private ManageTable manageTable;
//	private ManageCategory manageCategory;
//	private ManageFood manageFood;
//	private ManageDiscount manageDiscount;
//	private ManageCustomer manageCustomer;
//	private ManageEmployee manageEmployee;
//	private ManageManager manageManager;
//
//	public VBox getManagerComponent() {
//		return managerComponent;
//	}
//
//	public ManageIncome getManageIncome() {
//		return manageIncome;
//	}
//
//	public ManageBill getManageBill() {
//		return manageBill;
//	}
//
//	public ManageTable getManageTable() {
//		return manageTable;
//	}
//
//	public ManageCategory getManageCategory() {
//		return manageCategory;
//	}
//
//	public ManageFood getManageFood() {
//		return manageFood;
//	}
//
//	public ManageDiscount getManageDiscount() {
//		return manageDiscount;
//	}
//
//	public ManageCustomer getManageCustomer() {
//		return manageCustomer;
//	}
//
//	public ManageEmployee getManageEmployee() {
//		return manageEmployee;
//	}
//
//	public ManageManager getManageManager() {
//		return manageManager;
//	}
//
//	@Override
//	public void initialize(URL url, ResourceBundle resourceBundle) {
//		manageIncome = new ManageIncome(this);
//		manageBill = new ManageBill(this);
//		manageTable = new ManageTable(this);
//		manageCategory = new ManageCategory(this);
//		manageFood = new ManageFood(this);
//		manageDiscount = new ManageDiscount(this);
//		manageCustomer = new ManageCustomer(this);
//		manageEmployee = new ManageEmployee(this);
//		manageManager = new ManageManager(this);
//
//		incomeComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageIncome));
//		billComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageBill));
//		tableComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageTable));
//		categoryComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageCategory));
//		foodComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageFood));
//		discountComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageDiscount));
//		customerComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageCustomer));
//		employeeComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageEmployee));
//		managerComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/ManagePane.fxml", manageManager));
//	}
}
