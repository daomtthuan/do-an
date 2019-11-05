package controller.customer;

import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import component.controller.BillDetailPane;
import component.controller.MenuPane;
import controller.ChangePassword;
import controller.Controller;
import controller.WatchAccount;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.BillDetail;
import model.Discount;
import model.Food;
import org.jetbrains.annotations.NotNull;
import tool.Number;

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

	Order() {
		SecondaryStage.getInstance().setOrdering(true);
	}

	

	private void setup() {
		if (SecondaryStage.getInstance().getAccount() == null) {
			informationButton.setText("Register");
			informationButton.setOnAction(event -> {
				DialogSecondaryStage.getInstance().setScene("/view/Account.fxml", "/style/general/Account.css", new Register());
				DialogSecondaryStage.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});

			accountButton.setText("Login");
			accountButton.setOnAction(event -> {
				DialogSecondaryStage.getInstance().setScene("/view/Login.fxml", new Login());
				DialogSecondaryStage.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});
		} else {
			informationButton.setText("Account");
			informationButton.setOnAction(event -> {
				DialogSecondaryStage.getInstance().setScene("/view/WatchAccount.fxml", "/style/general/Account.css", new WatchAccount(SecondaryStage.getInstance().getAccount()));
				DialogSecondaryStage.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});

			accountButton.setText("Change Password");
			accountButton.setOnAction(event -> {
				DialogSecondaryStage.getInstance().setScene("/view/ChangePassword.fxml", new ChangePassword(SecondaryStage.getInstance().getAccount()));
				DialogSecondaryStage.getInstance().getStage().show();
				SecondaryStage.getInstance().getStage().hide();
			});
		}

		if (SecondaryStage.getInstance().getDiscount() == null) {
			discountButton.setText("Enter Discount Code");
		} else {
			discountButton.setText("Change Discount Code");
		}

		discountButton.setOnAction(event -> {
			DialogSecondaryStage.getInstance().setScene("/view/customer/EnterDiscount.fxml", new EnterDiscount());
			DialogSecondaryStage.getInstance().getStage().show();
			SecondaryStage.getInstance().getStage().hide();
		});
	}

	@FXML
	private void submit() {
		if (SecondaryStage.getInstance().getBillDetails().size() > 0) {
			SecondaryStage.getInstance().setScene("/view/customer/SelectTable.fxml", "/style/customer/SelectTable.css", new SelectTable());
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		BillDetailPane billDetailPane = new BillDetailPane() {
			@Override
			public void setup() {
				setTotalBefore(Number.round(getTotalBefore(), 2));
				setSale(Number.round(getSale(), 2));
				setTotal(Number.round(getTotal(), 2));
			}
		};

		MenuPane menuPane = new MenuPane() {
			@Override
			public void selectFood(@NotNull Food food) {
				boolean found = false;
				for (BillDetail billDetail : SecondaryStage.getInstance().getBillDetails()) {
					if (billDetail.getIdFood() == food.getId()) {
						found = true;
						billDetail.setQuantity(billDetail.getQuantity() + 1);
						break;
					}
				}
				if (!found) {
					SecondaryStage.getInstance().getBillDetails().add(new BillDetail(food.getId(), food.getName(), food.getPrice(), 1));
				}
				billDetailPane.getBillDetailTableView().getItems().clear();
				billDetailPane.getBillDetailTableView().setItems(FXCollections.observableArrayList(SecondaryStage.getInstance().getBillDetails()));
				billDetailPane.calculatePrice();
			}
		};

		menuComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/MenuPane.fxml", menuPane));
		billDetailComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillDetailPane.fxml", billDetailPane));

		billDetailPane.getBillDetailTableView().setItems(FXCollections.observableArrayList(SecondaryStage.getInstance().getBillDetails()));
		billDetailPane.calculate(getTotalBefore(), getSale(), getTotal());

		setup();
		SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
			windowEvent.consume();
			SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css", new Customer());
		});
		SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> {
			setup();
			billDetailPane.calculate(getTotalBefore(), getSale(), getTotal());
		});
	}
}
