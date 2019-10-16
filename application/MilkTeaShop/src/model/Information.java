package model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Information {
    private int id;
    private String name;
    private boolean gender;
    private String birthday;
    private String address;
    private int phone;
    private String email;

    @Contract(pure = true)
    public Information(int id, String name, boolean gender, String birthday, String address, int phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public String getBirthday() {
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

