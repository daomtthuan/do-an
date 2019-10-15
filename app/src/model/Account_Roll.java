package model;

public class Account_Roll {
    private int id;
    private Account idAccount;
    private Roll idRoll;

    public Account_Roll() {
    }

    public Account_Roll(int id, Account idAccount, Roll idRoll) {
        this.id = id;
        this.idAccount = idAccount;
        this.idRoll = idRoll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }

    public Roll getIdRoll() {
        return idRoll;
    }

    public void setIdRoll(Roll idRoll) {
        this.idRoll = idRoll;
    }
}
