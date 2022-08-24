package com.revature.saltwater.services;

import com.revature.saltwater.daos.ProductDAO;
import com.revature.saltwater.models.Product;

import java.util.List;

public class ProductServices {
    private final ProductDAO productDAO;

    public ProductServices(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getProduct(String id) {
        return productDAO.getById(id);
    }

    public void subtractQuantity(Product obj) {
        productDAO.subtractQuantity(obj);
    }

    public void addQuantity(Product obj) {
        productDAO.addQuantity(obj);
    }

    public List<Product> getByWhID(String id) {
        return productDAO.getByWhID(id);
    }

    public void replenishQuantity(Product obj) {
        productDAO.replenishQuantity(obj);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }


}