package com.revature.saltwater.daos;

import com.revature.saltwater.models.User;
import com.revature.saltwater.utils.customexceptions.InvalidSQLException;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {


    @Override
    public void save(User obj) {

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, buyer, seller) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setBoolean(4, obj.isBuyer());
            ps.setBoolean(5, obj.isSeller());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to connect to the database.");
        }

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("username");
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to connect to the database.");
        }
        return null;
    }

    @Override
    public List<String> getAllUsernames() {
        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("buyer"), rs.getBoolean("seller"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

}
