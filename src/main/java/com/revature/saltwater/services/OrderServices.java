package com.revature.saltwater.services;

import com.revature.saltwater.daos.OrderDAO;
import com.revature.saltwater.daos.ProductDAO;
import com.revature.saltwater.models.Order;
import com.revature.saltwater.models.Product;

import java.util.List;

public class OrderServices {
    private final OrderDAO orderDAO;

    public OrderServices(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    public List<Order> getAllOrders(String userID) {
        return orderDAO.getAllOrders(userID);
    }
}
