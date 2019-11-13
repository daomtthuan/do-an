package app.primary;

import app.pattern.Stage;
import model.Account;

public class PrimaryStage extends Stage {
	private static PrimaryStage instance;
	private Account account;

	public static PrimaryStage getInstance() {
		if (instance == null) {
			setInstance(new PrimaryStage());
		}
		return instance;
	}

	private static void setInstance(PrimaryStage instance) {
		PrimaryStage.instance = instance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
