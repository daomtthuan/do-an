package controller.employee;

import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Table;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeTable implements Controller, Initializable {
	@FXML
	private ComboBox<Table> tableComboBox;
	private Table oldTable;
	private Runnable refresh;

	public ChangeTable(Table oldTable, Runnable refresh) {
		this.oldTable = oldTable;
		this.refresh = refresh;
	}

	@FXML
	private void submit() {
		if (!tableComboBox.getSelectionModel().isEmpty()) {
			if (api.Table.getInstance().changeTable(oldTable.getId(), tableComboBox.getSelectionModel().getSelectedItem().getId()) != null) {
				refresh.run();
				PrimaryDialog.getInstance().close();
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Cannot change table.\nPlease notify staff.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Cannot change table.\nPlease choose table to change.");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tableComboBox.setItems(FXCollections.observableArrayList(api.Table.getInstance().getNotBusyTables()));
	}
}
