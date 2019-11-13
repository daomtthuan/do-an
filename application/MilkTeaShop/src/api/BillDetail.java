package api;

import app.alert.AlertError;
import app.pattern.Api;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail implements Api {
	private static BillDetail instance;

	public static BillDetail getInstance() {
		if (instance == null) {
			setInstance(new BillDetail());
		}
		return instance;
	}

	private static void setInstance(BillDetail instance) {
		BillDetail.instance = instance;
	}

	public model.BillDetail insert(int idBill, int idFood, String nameFood, int idCategory, String nameCategory, int quantity, double price) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBillDetail] ? , ? , ? , ? , ? , ? , ?", new Object[] {idBill, idFood, nameFood, idCategory, nameCategory, quantity, price});
			assert resultSet != null;
			return resultSet.next() ? new model.BillDetail(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
