package com.example.myshop.Model;

public class Admin {
    private String name, phone, password, email;
    public Admin()
    {

    }
    public Admin(String name, String phone, String password, String email)
    {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
