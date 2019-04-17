package com.example.recyclerx.Model;

public class User
{
    private String id;
    private String username;
    private String mail;
    private String address;

    public User() {
      }

    public User(String id, String username, String mail, String address) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
