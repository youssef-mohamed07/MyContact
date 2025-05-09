package com.mycompany.mycontacts;

public class User {
    private int id;
    private String username;
    private String email;
    private String phone;
    private String work;

    public User(int id, String username, String email, String phone, String work) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.work = work;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
} 