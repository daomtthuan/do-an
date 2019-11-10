package component.controller;

import app.pattern.Controller;
import controller.manager.Manager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.jetbrains.annotations.Contract;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ManagePane implements Controller, Initializable {
	@FXML
	private Button insertButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button deleteButton;
	private Manager manager;

	@Contract(pure = true)
	public ManagePane(Manager manager) {
		this.manager = manager;
	}

	protected Manager getManager() {
		return manager;
	}

	@FXML
	protected abstract void setup();

	@FXML
	protected abstract void load();

	@FXML
	protected abstract void insert();

	@FXML
	protected abstract void update();

	@FXML
	protected abstract void delete();

	@FXML
	protected abstract void refresh();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		insertButton.setOnAction(actionEvent -> insert());
		updateButton.setOnAction(actionEvent -> update());
		deleteButton.setOnAction(actionEvent -> delete());
		setup();
		load();
	}
}
