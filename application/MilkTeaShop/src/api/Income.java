package api;

import app.alert.AlertError;
import app.pattern.Api;
import model.Buy;
import model.Sell;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Income implements Api {
	private static Income instance;

	public static Income getInstance() {
		if (instance == null) {
			setInstance(new Income());
		}
		return instance;
	}

	private static void setInstance(Income instance) {
		Income.instance = instance;
	}

	public Buy getTopBuy() {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select top(1) * from [Buy] order by [income] desc");
			assert resultSet != null;
			return resultSet.next() ? new Buy(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public Sell getTopSell() {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select top(1) * from [Sell] order by [quantity] desc, [income] desc");
			assert resultSet != null;
			return resultSet.next() ? new Sell(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public ArrayList<model.Income> getDayIncomes(String from, String to) {
		ArrayList<model.Income> incomes = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Income] where '" + from + " 00:00:00' <= [date] and [date] <= '" + to + " 23:59:59.999'");
			assert resultSet != null;
			while (resultSet.next()) {
				incomes.add(new model.Income(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return incomes;
	}
}
