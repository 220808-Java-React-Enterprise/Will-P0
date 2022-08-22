package com.revature.saltwater.services;

import com.revature.saltwater.daos.ProductDAO;
import com.revature.saltwater.models.Product;

import java.util.List;

public class ProductServices {
    private final ProductDAO productDAO;

    public ProductServices(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }
}