package com.revature.saltwater.daos;

import com.revature.saltwater.models.Delivery;
import com.revature.saltwater.models.Order;
import com.revature.saltwater.utils.customexceptions.InvalidSQLException;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO implements CrudDAO<Delivery> {

    @Override
    public void save(Delivery obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO deliveries (id, date, seller_id, product_id) VALUES (?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getDate());
            ps.setString(3, obj.getSeller_id());
            ps.setString(4, obj.getProduct_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Delivery obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Delivery getById(String id) {
        return null;
    }

    @Override
    public List<Delivery> getAll() {
        return null;
    }


    public List<Delivery> getAllDeliveries(String seller_id) {
        List<Delivery> deliveries = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM deliveries WHERE seller_id = ?");
            ps.setString(1, seller_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Delivery delivery = new Delivery(rs.getString("id"), rs.getString("date"), rs.getString("seller_id"), rs.getString("product_id"));
                deliveries.add(delivery);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return deliveries;
    }
}
