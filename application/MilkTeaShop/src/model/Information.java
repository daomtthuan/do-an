package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Information {

    private int id;
    private String name;
    private boolean gender;
    private Date birthday;
    private String address;
    private int phone;
    private String email;

    @Contract(pure = true)
    public Information() {
    }

    @Contract(pure = true)
    public Information(int id, String name, boolean gender, Date birthday, String address, int phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Contract(pure = true)
    public Information(@NotNull Information information) {
        this.id = information.id;
        this.name = information.name;
        this.gender = information.gender;
        this.birthday = information.birthday;
        this.address = information.address;
        this.phone = information.phone;
        this.email = information.email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
