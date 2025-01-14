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

	public ArrayList<model.Bill> getBills(int idTable, String from, String to) {
		ArrayList<model.Bill> bills = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [BillList] where [idTable] = " + idTable + " and '" + from + " 00:00:00.000' <= [checkIn] and [checkIn] <= '" + to + " 23:59:59.999' ");
			assert resultSet != null;
			while (resultSet.next()) {
				bills.add(new model.Bill(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return bills;
	}

	public model.Bill getNotCheckoutBill(int idTable) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [BillList] where [idTable] = " + idTable + " and [checkOut] is null");
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Bill insert(Table table, Account customer, Account employee, Discount discount, double sale) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBill] ? , ? , ? , ? , ?", new Object[] {table.getId(), customer != null ? customer.getId() : null, employee.getId(), discount != null ? discount.getId() : null, sale});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Bill checkOut(int idTable) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [checkoutBill] ?", new Object[] {idTable});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
