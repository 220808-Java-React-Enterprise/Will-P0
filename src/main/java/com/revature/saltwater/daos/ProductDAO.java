package com.revature.saltwater.daos;

import com.revature.saltwater.models.Delivery;
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
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getString("id"), rs.getString("name"), rs.getString("type"), rs.getString("brand"), rs.getString("price"), rs.getString("quantity"), rs.getString("warehouse_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to connect to the database.");
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product prod = new Product(rs.getString("id"), rs.getString("name"), rs.getString("type"), rs.getString("brand"), rs.getString("price"), rs.getString("quantity"), rs.getString("warehouse_id"));
                products.add(prod);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return products;
    }

    public void subtractQuantity(Product obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            int newQuantity = Integer.parseInt(obj.getQuantity())-1;
            PreparedStatement ps = con.prepareStatement("UPDATE products SET quantity = ? WHERE id = ?");
            ps.setInt(1, newQuantity);
            ps.setString(2, obj.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void addQuantity(Product obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            int newQuantity = Integer.parseInt(obj.getQuantity())+1;
            PreparedStatement ps = con.prepareStatement("UPDATE products SET quantity = ? WHERE id = ?");
            ps.setInt(1, newQuantity);
            ps.setString(2, obj.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void replenishQuantity(Product obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            int newQuantity = 40;
            PreparedStatement ps = con.prepareStatement("UPDATE products SET quantity = ? WHERE id = ?");
            ps.setInt(1, newQuantity);
            ps.setString(2, obj.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Product> getByWhID(String warehouse_id) {
        List<Product> products = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE warehouse_id = ?");
            ps.setString(1, warehouse_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("type"), rs.getString("brand"), rs.getString("price"), rs.getString("quantity"), rs.getString("warehouse_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return products;
    }


}
