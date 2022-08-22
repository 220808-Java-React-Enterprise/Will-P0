package com.revature.saltwater.daos;

import com.revature.saltwater.models.Order;
import com.revature.saltwater.models.Product;
import com.revature.saltwater.utils.customexceptions.InvalidSQLException;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Order> {


    @Override
    public void save(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, user_id, product_id) VALUES (?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getProduct_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }


    public List<Order> getAllOrders(String user_id) {
        List<Order> orders = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getString("id"), rs.getString("user_id"), rs.getString("product_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return orders;
    }



}