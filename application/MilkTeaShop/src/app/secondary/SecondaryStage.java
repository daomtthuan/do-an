package app.secondary;

import app.alert.AlertError;
import app.pattern.Controller;
import app.pattern.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import model.Account;
import model.BillDetail;
import model.Discount;
import model.Table;

import java.io.IOException;
import java.util.ArrayList;

public class SecondaryStage extends Stage {
	private static SecondaryStage instance;
	private Account account;
	private Discount discount;
	private ArrayList<BillDetail> billDetails;
	private Table table;
	private double x;
	private double y;
	private double width;
	private double height;

	private SecondaryStage() {
		setStage(new javafx.stage.Stage());
		getStage().setTitle("Milk Tea Shop - Customer");
		getStage().getIcons().add(new Image(getClass().getResourceAsStream("/asset/brand/Logo.png")));
		account = null;
		discount = null;
		billDetails = null;
		table = null;
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	public static SecondaryStage getInstance() {
		if (instance == null) {
			setInstance(new SecondaryStage());
		}
		return instance;
	}

	private static void setInstance(SecondaryStage instance) {
		SecondaryStage.instance = instance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public boolean isNotOrdering() {
		return billDetails == null;
	}

	public ArrayList<BillDetail> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(ArrayList<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public void setScene(String view, Controller controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			loader.setController(controller);
			Scene scene = new Scene(loader.load());
			new JMetro(scene, Style.LIGHT);
			scene.getStylesheets().add("/style/general/Style.css");
			getStage().setScene(scene);
			setSize();
			SecondaryStage.getInstance().getStage().setOnShown(windowEvent -> setSize());
		} catch (IOException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	@Override
	public void setScene(String view, String style, Controller controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
			loader.setController(controller);
			Scene scene = new Scene(loader.load());
			new JMetro(scene, Style.LIGHT);
			scene.getStylesheets().add("/style/general/Style.css");
			scene.getStylesheets().add(style);
			getStage().setScene(scene);
			setSize();
			SecondaryStage.getInstance().getStage().setOnShown(windowEvent -> setSize());
		} catch (IOException e) {
			AlertError.getInstance().showAndWait(e);
		}
	}

	private void setSize() {
		if (x != 0) {
			getStage().setX(x);
			getStage().setY(y);
			getStage().setWidth(width);
			getStage().setHeight(height);
			getStage().setMaximized(true);
		}
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
