package com.revature.saltwater.daos;

import com.revature.saltwater.models.Product;
import com.revature.saltwater.utils.customexceptions.InvalidSQLException;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CrudDAO<Product> {
    @Override
    public void save(Product obj) {

    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product prod = new Product(rs.getString("id"), rs.getString("name"), rs.getString("type"), rs.getString("brand"), rs.getString("price"));
                products.add(prod);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return products;
    }


}
