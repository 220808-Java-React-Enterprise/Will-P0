package com.revature.saltwater.models;

public class Order {

    private String id;
    private String user_id;
    private String product_id;

    private Order() {

    }

    public Order(String id, String user_id, String product_id) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
