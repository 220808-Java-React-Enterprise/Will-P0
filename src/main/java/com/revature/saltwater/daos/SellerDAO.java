package com.revature.saltwater.daos;

import com.revature.saltwater.models.Seller;
import com.revature.saltwater.utils.customexceptions.InvalidSQLException;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDAO implements CrudDAO<Seller> {

    @Override
    public void save(Seller obj) {

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO sellers (id, username, password) VALUES (?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to connect to the database.");
        }

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Seller getById(String id) {
        return null;
    }

    @Override
    public List<Seller> getAll() {
        return null;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM sellers WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("username");
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to connect to the database.");
        }
        return null;
    }



    public Seller getUserByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sellers WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new Seller(rs.getString("id"), rs.getString("username"), rs.getString("password"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }
}
