package controller.customer;

import app.stage.DialogSecondaryStage;
import app.stage.SecondaryStage;
import app.tool.Number;
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
import model.BillDetail;
import model.Discount;
import model.Food;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Order implements Controller, Initializable {
    @FXML
    private AnchorPane menuComponent;
    @FXML
    private AnchorPane billDetailComponent;
    @FXML
    private Button discountButton;
    @FXML
    private Button informationButton;
    @FXML
    private Button accountButton;
    private ArrayList<BillDetail> billDetails;

    @Contract(pure = true)
    public Order() {
        SecondaryStage.getInstance().setOrdering(true);
        billDetails = new ArrayList<>();
    }

    @Contract(pure = true)
    public Order(ArrayList<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    private double getTotalBefore() {
        double total = 0;
        for (BillDetail billDetail : billDetails) {
            total += billDetail.getTotal();
        }
        return total;
    }

    private double getSale() {
        double sale = 0;
        Discount discount = SecondaryStage.getInstance().getDiscount();
        if (SecondaryStage.getInstance().getAccount() != null) {
            sale += 2;
        }
        if (discount != null) {
            sale += discount.getSale();
        }
        return sale;
    }

    private double getTotal() {
        double total = getTotalBefore();
        return total - (total * getSale() / 100.0);
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
            DialogSecondaryStage.getInstance().setScene("/view/customer/EnterDiscount.fxml");
            DialogSecondaryStage.getInstance().getStage().show();
            SecondaryStage.getInstance().getStage().hide();
        });
    }

    @FXML
    private void submit() {
        SecondaryStage.getInstance().setScene("/view/customer/SelectTableFood.fxml", new SelectTableFood(billDetails));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BillDetailPane billDetailPane = new BillDetailPane() {
            @Override
            public void calculate(double totalBefore, double sale, double total) {
                setTotalBefore(Number.round(totalBefore, 2));
                setSale(Number.round(sale, 2));
                setTotal(Number.round(total, 2));
            }
        };

        MenuPane menuPane = new MenuPane() {
            @Override
            public void selectFood(@NotNull Food food) {
                boolean found = false;
                for (model.BillDetail billDetail : billDetails) {
                    if (billDetail.getIdFood() == food.getId()) {
                        found = true;
                        billDetail.setQuantity(billDetail.getQuantity() + 1);
                        break;
                    }
                }
                if (!found) {
                    billDetails.add(new model.BillDetail(food.getId(), food.getName(), food.getPrice(), 1));
                }
                billDetailPane.getBillDetailTableView().getItems().clear();
                billDetailPane.getBillDetailTableView().setItems(FXCollections.observableArrayList(billDetails));
                billDetailPane.calculate(getTotalBefore(), getSale(), getTotal());
            }
        };

        menuComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/MenuPane.fxml", menuPane));
        billDetailComponent.getChildren().add(SecondaryStage.getInstance().loadComponent("/component/view/BillDetailPane.fxml", billDetailPane));
        if (billDetails.size() > 0) {
            billDetailPane.getBillDetailTableView().setItems(FXCollections.observableArrayList(billDetails));
        }
        billDetailPane.calculate(getTotalBefore(), getSale(), getTotal());

        setup();
        SecondaryStage.getInstance().getStage().setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            SecondaryStage.getInstance().setScene("/view/customer/Customer.fxml", "/style/customer/Customer.css");
            SecondaryStage.getInstance().setAccount(null);
            SecondaryStage.getInstance().setDiscount(null);
            SecondaryStage.getInstance().setOrdering(false);
        });

        SecondaryStage.getInstance().getStage().setOnShowing(windowEvent -> {
            setup();
            billDetailPane.calculate(getTotalBefore(), getSale(), getTotal());
        });
    }
}
