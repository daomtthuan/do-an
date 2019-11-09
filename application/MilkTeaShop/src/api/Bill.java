package api;

import app.alert.AlertError;
import app.pattern.Api;
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
	public model.Bill insert(int idTable, int idEmployee, String nameDiscount, double sale){
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBillWithoutCustomer] ? , ? , ? , ?", new Object[] {idTable, idEmployee, nameDiscount, sale});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	@Nullable
	public model.Bill insert(int idTable, int idCustomer, int idEmployee, String nameDiscount, double sale){
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBillWithCustomer] ? , ? , ? , ? , ?", new Object[] {idTable, idCustomer, idEmployee, nameDiscount, sale});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
