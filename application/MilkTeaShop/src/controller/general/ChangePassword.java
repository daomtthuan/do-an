package controller.general;

import app.alert.AlertInformation;
import app.alert.AlertWarning;
import app.pattern.Controller;
import app.primary.PrimaryDialog;
import app.primary.PrimaryStage;
import app.secondary.SecondaryDialog;
import app.secondary.SecondaryStage;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Account;
import tool.Regex;

public class ChangePassword implements Controller {
	private Account account;
	@FXML
	private PasswordField oldPasswordField;
	@FXML
	private PasswordField newPasswordField;
	@FXML
	private PasswordField repeatPasswordField;

	public ChangePassword(Account account) {
		this.account = account;
	}

	@FXML
	private void submit() {
		String oldPassword = oldPasswordField.getText();
		String newPassword = newPasswordField.getText();
		String repeatPassword = repeatPasswordField.getText();

		if (oldPassword.matches(Regex.PASSWORD) && newPassword.matches(Regex.PASSWORD) && repeatPassword.matches(Regex.PASSWORD)) {
			if (oldPassword.equals(account.getPassword())) {
				if (newPassword.equals(repeatPassword)) {
					Account newAccount = api.Account.getInstance().update(account.getId(), newPassword, account.getName(), account.isMale(), account.getBirthday(), account.getAddress(), account.getPhone(), account.getEmail());
					if (newAccount != null) {
						AlertInformation.getInstance().showAndWait("Success!", "Please remember your new password carefully.");
						if (account.getRoll() == 1) {
							SecondaryStage.getInstance().setAccount(newAccount);
							SecondaryDialog.getInstance().close();
						} else {
							PrimaryStage.getInstance().setAccount(newAccount);
							PrimaryDialog.getInstance().getStage().hide();
							PrimaryStage.getInstance().getStage().show();
						}
					} else {
						AlertWarning.getInstance().showAndWait("Fail!", "Can not change Password.\nPlease notify staff.");
					}
				} else {
					AlertWarning.getInstance().showAndWait("Fail!", "Repeat Password does not match.");
				}
			} else {
				AlertWarning.getInstance().showAndWait("Fail!", "Password is incorrect.");
			}
		} else {
			AlertWarning.getInstance().showAndWait("Fail!", "Password is incorrect.");
		}
	}
}
