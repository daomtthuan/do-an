package controller.customer;

import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimarySubDialog;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import component.controller.BillDetailPane;
import component.controller.MenuPane;
import controller.employee.ManageOrder;
import controller.general.ChangePassword;
import controller.general.WatchAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BillDetail;
import model.Category;
import model.Food;

import java.net.URL;
import java.util.ResourceBundle;

class Order implements Controller, Initializable {
	@FXML
	private AnchorPane menuComponent;
	@FXML
	private VBox billDetailComponent;
	@FXML
	private Button discountButton;
	@FXML
	private Button informationButton;
	@FXML
	private Button accountButton;
	private BillDetailPane billDetailPane;
	private ManageOrder manageOrder;

	private void setup(BillDetailPane billDetailPane) {
		if (SecondaryStage.getInstance().getAccount() == null) {
			informationButton.setText("Register");
			informationButton.setOnAction(event -> {
				SecondaryDialog.getInstance().setScene("/view/general/Account.fxml", "/style/general/Account.css", new Register());
				SecondaryDialog.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});

			accountButton.setText("Login");
			accountButton.setOnAction(event -> {
				SecondaryDialog.getInstance().setScene("/view/general/Login.fxml", new Login());
				SecondaryDialog.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});
		} else {
			informationButton.setText("Account");
			informationButton.setOnAction(event -> {
				SecondaryDialog.getInstance().setScene("/view/general/WatchAccount.fxml", "/style/general/Account.css", new WatchAccount(SecondaryStage.getInstance().getAccount()));
				SecondaryDialog.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});

			accountButton.setText("Change Password");
			accountButton.setOnAction(event -> {
				SecondaryDialog.getInstance().setScene("/view/general/ChangePassword.fxml", new ChangePassword(SecondaryStage.getInstance().getAccount()));
				SecondaryDialog.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});
		}

		if (SecondaryStage.getInstance().getDiscount() == null) {
			discountButton.setText("Enter Discount Code");
		} else {
			discountButton.setText("Change Discount Code");
		}

		discountButton.setOnAction(event -> {
			SecondaryDialog.getInstance().setScene("/view/customer/EnterDiscount.fxml", new EnterDiscount());
			SecondaryDialog.getInstance().getStage().show();
			SecondaryStage.getInstance().getStage().hide();
		});

		billDetailPane.setBillDetails(SecondaryStage.getInstance().getAccount(), SecondaryStage.getInstance().getDiscount(), SecondaryStage.getInstance().getBillDetails());
	}

	@FXML
	private void submit() {
		if (SecondaryStage.getInstance().getBillDetails().size() > 0) {
			SecondaryStage.getInstance().setScene("/view/customer/SelectTable.fxml", "/style/customer/SelectTable.css", new SelectTable(manageOrder));
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		manageOrder = new ManageOrder();
		PrimaryDialog.getInstance().setScene("/view/employee/ManageOrder.fxml", "/style/employee/ManageOrder.css", manageOrder);

		BillDetailPane billDetailPane = new BillDetailPane();
		MenuPane menuPane = new MenuPane() {
			@Override
			public void selectFood(Category category, Food food) {
				boolean found = false;
				for (BillDetail billDetail : SecondaryStage.getInstance().getBillDetails()) {
					if (billDetail.getIdFood() == food.getId()) {
						found = true;
						billDetail.setQuantity(billDetail.getQuantity() + 1);
						break;
					}
				}
				if (!found) {
					SecondaryStage.getInstance().getBillDetails().add(new BillDetail(category, food));
				}
				billDetailPane.setBillDetails(SecondaryStage.getInstance().getAccount(), SecondaryStage.getInstance().getDiscount(), SecondaryStage.getInstance().getBillDetails());
				manageOrder.getBillPane().setBillDetails(SecondaryStage.getInstance().getAccount(), SecondaryStage.getInstance().getDiscount(), SecondaryStage.getInstance().getBillDetails());
			}
		};

		menuComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/MenuPane.fxml", menuPane));
		billDetailComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillDetailPane.fxml", billDetailPane));

		setup(billDetailPane);
		manageOrder.getBillPane().setup();
		manageOrder.setupCustomerButton();

		SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
			windowEvent.consume();
			SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css", new Customer());
			PrimaryDialog.getInstance().getStage().hide();
			PrimarySubDialog.getInstance().getStage().hide();
		});

		SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> {
			setup(billDetailPane);
			manageOrder.getBillPane().setup();
			manageOrder.setupCustomerButton();
		});

		PrimaryDialog.getInstance().getStage().show();
	}
}
