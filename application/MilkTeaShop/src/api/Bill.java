package api;

import app.alert.AlertError;
import app.pattern.Api;
import model.Account;
import model.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Bill implements Api {
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

	@Nullable
	public model.Bill insert(@NotNull Table table, Account customer, Account employee, Discount discount, double sale){
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBillWithCustomer] ? , ? , ? , ? , ?", new Object[] {
					table.getId(), customer != null ? customer.getId() : null, idEmployee, nameDiscount, sale
			});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
