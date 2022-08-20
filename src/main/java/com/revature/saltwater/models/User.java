package com.revature.saltwater.models;

public class User {

    private String id;
    private String username;
    private String password;
    private boolean buyer;
    private boolean seller;

    public User() {

    }

    public User(String id, String username, String password, boolean buyer, boolean seller) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.buyer = buyer;
        this.seller = seller;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBuyer() {
        return buyer;
    }

    public void setBuyer(boolean buyer) {
        this.buyer = buyer;
    }

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", buyer=" + buyer +
                ", seller=" + seller +
                '}';
    }
}
