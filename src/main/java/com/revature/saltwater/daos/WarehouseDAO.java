package com.revature.saltwater.daos;

import com.revature.saltwater.models.Product;
import com.revature.saltwater.models.Warehouse;
import com.revature.saltwater.services.WarehouseServices;
import com.revature.saltwater.utils.customexceptions.InvalidSQLException;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements CrudDAO<Warehouse>{

    @Override
    public void save(Warehouse obj) {

    }

    @Override
    public void update(Warehouse obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Warehouse getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouses WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Warehouse(rs.getString("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to connect to the database.");
        }
        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        List<Warehouse> warehouses = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouses");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Warehouse wh = new Warehouse(rs.getString("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"));
                warehouses.add(wh);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
        return warehouses;

    }
}
