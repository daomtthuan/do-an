package app.secondary;

import app.Stage;
import model.Account;
import model.BillDetail;
import model.Discount;
import model.Table;

import java.util.ArrayList;

public class SecondaryStage extends Stage {
	private static SecondaryStage instance;
	private Account account;
	private Discount discount;
	private ArrayList<BillDetail> billDetails;
	private Table table;

	private SecondaryStage() {
		setStage(new javafx.stage.Stage());
		getStage().setTitle("Milk Tea Shop - Customer");

		account = null;
		discount = null;
		billDetails = null;
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

	public boolean isOrdering() {
		return billDetails != null;
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

	public double getTotalBefore() {
		double totalBefore = 0;
		for (BillDetail billDetail : billDetails) {
			totalBefore += billDetail.getTotal();
		}
		return totalBefore;
	}

	public double getSale() {
		double sale = 0;
		if (account != null) {
			sale += 2;
		}
		if (discount != null) {
			sale += discount.getSale();
		}
		return sale;
	}

	public double getTotal() {
		double total = getTotalBefore();
		return total - (total * getSale() / 100.0);
	}
}
