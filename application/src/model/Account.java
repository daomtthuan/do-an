package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Account {
    private int id;
    private String name;
    private String password;
    private Information information;

    @Contract(pure = true)
    public Account() {
    }

    @Contract(pure = true)
    public Account(int id, String name, String password, Information information) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.information = information;
    }

    @Contract(pure = true)
    public Account(@NotNull Account account) {
        this.id = account.id;
        this.name = account.name;
        this.password = account.password;
        this.information = new Information(account.information);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Information getInformation() {
        return information;
    }
}
