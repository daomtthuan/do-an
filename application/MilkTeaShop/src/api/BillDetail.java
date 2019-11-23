package api;

import app.alert.AlertError;
import app.pattern.Api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<model.BillDetail> getBillDetails(int idBill) {
		ArrayList<model.BillDetail> billDetails = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [BillDetail] where [idBill] = " + idBill);
			assert resultSet != null;
			while (resultSet.next()) {
				billDetails.add(new model.BillDetail(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return billDetails;
	}

	public model.Bill insert(int idBill, int idFood, String nameFood, int idCategory, String nameCategory, int quantity, double price) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [insertBillDetail] ? , ? , ? , ? , ? , ? , ?", new Object[] {idBill, idFood, nameFood, idCategory, nameCategory, quantity, price});
			assert resultSet != null;
			return resultSet.next() ? new model.Bill(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
