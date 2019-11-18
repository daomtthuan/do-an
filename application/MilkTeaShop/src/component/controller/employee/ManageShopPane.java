package component.controller.employee;

import app.pattern.Controller;
import app.primary.PrimaryStage;
import component.controller.general.TablePane;
import component.controller.general.managepane.ManageBillPane;
import component.controller.general.managepane.ManageDiscountPane;
import component.controller.general.managepane.ManageTablePane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Table;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageShopPane implements Controller, Initializable {
	@FXML
	private VBox subManageComponent;

	@FXML
	private void managePositionTable() {
		subManageComponent.getChildren().clear();
		TablePane tablePane = new TablePane() {
			@Override
			protected void setup() {
				ArrayList<Table> tables = api.Table.getInstance().getEnabledTables();
				tables.forEach(table -> {
					Button tableButton = createButton(table);
					getTablePane().getChildren().add(tableButton);
				});
			}
		};
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/TablePane.fxml", tablePane));
	}

	@FXML
	private void manageTable() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageTablePane()));
	}

	@FXML
	private void manageBill() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManageBillPane.fxml", new ManageBillPane()));
	}

	@FXML
	private void manageDiscount() {
		subManageComponent.getChildren().clear();
		subManageComponent.getChildren().add(PrimaryStage.getInstance().loadComponent("/component/view/general/managepane/ManagePane.fxml", new ManageDiscountPane()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		managePositionTable();
	}
}
