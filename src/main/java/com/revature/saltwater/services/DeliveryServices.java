package com.revature.saltwater.services;

import com.revature.saltwater.daos.DeliveryDAO;
import com.revature.saltwater.daos.OrderDAO;
import com.revature.saltwater.daos.ProductDAO;
import com.revature.saltwater.models.Delivery;
import com.revature.saltwater.models.Order;
import com.revature.saltwater.models.Product;

import java.util.List;

public class DeliveryServices {
    private final DeliveryDAO deliveryDAO;

    public DeliveryServices(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    public void saveDelivery(Delivery delivery) {
        deliveryDAO.save(delivery);
    }

    public List<Delivery> getAllDeliveries(String sellerID) {
        return deliveryDAO.getAllDeliveries(sellerID);
    }
}