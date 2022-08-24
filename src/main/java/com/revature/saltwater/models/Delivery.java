package com.revature.saltwater.models;

public class Delivery {
    private String id;
    private String date;
    private String seller_id;
    private String product_id;

    public Delivery(String id, String date, String seller_id, String product_id) {
        this.id = id;
        this.date = date;
        this.seller_id = seller_id;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
