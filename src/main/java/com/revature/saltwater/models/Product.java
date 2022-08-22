package com.revature.saltwater.models;

public class Product {

    private String id;
    private String name;
    private String type;
    private String brand;
    private String price;

    public Product() {

    }

    public Product(String id, String name, String type, String brand, String price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
