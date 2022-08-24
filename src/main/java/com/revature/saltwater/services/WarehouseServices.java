package com.revature.saltwater.services;

import com.revature.saltwater.daos.WarehouseDAO;
import com.revature.saltwater.models.Warehouse;

import java.util.List;

public class WarehouseServices {

    private final WarehouseDAO warehouseDAO;

    public WarehouseServices(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }


    public List<Warehouse> getAllWarehouses() {
        return warehouseDAO.getAll();
    }

}
