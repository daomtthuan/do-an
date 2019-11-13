package api;

import app.alert.AlertError;
import app.pattern.Api;
import model.Account;
import model.Discount;
import model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bill implements Api {
	private static Bill instance;

	public static Bill getInstance() {
		if (instance == null) {
			setInstance(new Bill());
		}
		return instance;
	}

	private static void setInstance(Bill instance) {
		Bill.instance = instance;
	}

	public ArrayList<model.Bill> getBills() {
		ArrayList<model.Bill> bills = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Bill]");
			assert resultSet != null;
			while (resultSet.next()) {
				bills.add(new model.Bill(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return bills;
	}

	public model.Bill insert(Table table, Account customer, Account employee, Discount discount, double sale) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBill] ? , ? , ? , ? , ? , ?", new Object[] {table.getId(), customer != null ? customer.getId() : null, employee.getId(), discount != null ? discount.getId() : null, discount != null ? discount.getName() : null, sale});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
