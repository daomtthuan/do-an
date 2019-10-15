package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AccountRoll {
    private int id;
    private Account account;
    private Roll roll;

    @Contract(pure = true)
    public AccountRoll() {
    }

    public AccountRoll(int id, Account account, Roll roll) {
        this.id = id;
        this.account = new Account(account);
        this.roll = new Roll(roll);
    }

    public AccountRoll(@NotNull AccountRoll accountRoll) {
        this.id = accountRoll.id;
        this.account = new Account(accountRoll.account);
        this.roll = new Roll(accountRoll.roll);
    }

    public int getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public Roll getRoll() {
        return roll;
    }
}
