package com.foresight.smartleadcollector.models;

public class User {

    private int id;
    private String email, name, phone;

    public User(int id, String email, String name, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getphone() {
        return phone;
    }
}
