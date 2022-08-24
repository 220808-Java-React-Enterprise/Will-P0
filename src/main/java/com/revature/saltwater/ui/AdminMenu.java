package com.revature.saltwater.ui;

import com.revature.saltwater.daos.ProductDAO;
import com.revature.saltwater.models.*;

import com.revature.saltwater.services.OrderServices;
import com.revature.saltwater.services.ProductServices;
import com.revature.saltwater.services.UserServices;
import com.revature.saltwater.services.WarehouseServices;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AdminMenu implements IMenu {

    private final ProductServices productServices;
    private final WarehouseServices warehouseServices;
    private final UserServices userServices;
    private final OrderServices orderServices;
    public AdminMenu(ProductServices productServices, WarehouseServices warehouseServices, UserServices userServices, OrderServices orderServices) {
        this.productServices = productServices;
        this.warehouseServices = warehouseServices;
        this.userServices = userServices;
        this.orderServices = orderServices;
    }



    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the admin menu!");
                System.out.println("\nWhat would you like to do?");
                System.out.println("[1] Replenish inventory");
                System.out.println("[2] View warehouse inventory");
                System.out.println("[3] View customers and their orders");
                System.out.println("[4] View orders by warehouse");
                System.out.println("[x] Exit");

                switch (scan.nextLine()) {
                    case "1":
                        viewProducts();
                        break;
                    case "2":
                        viewWarehouseInventory();
                        break;
                    case "3":
                        viewCustomers();
                        break;
                    case "4":
                        viewOrdersByWarehouse();
                        break;
                    case "x":
                        break exit;
                }
            }
        }

    }

    private void viewOrdersByWarehouse() {
        Scanner scan = new Scanner(System.in);
        exit: {
            while (true) {
                while (true) {
                    System.out.println("Select warehouse:");
                    List<Warehouse> warehouses = warehouseServices.getAllWarehouses();

                    for (int i = 0; i < warehouses.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + warehouses.get(i).getName());
                    }

                    int index = 0;
                    String n = scan.nextLine();
                    if (Objects.equals(n, "x")) {
                        return;
                    } else {
                        index = Integer.parseInt(n) - 1;
                    }
                    try {
                        Warehouse wh = warehouses.get(index);
                        List<Product> products = productServices.getByWhID(wh.getId());
                        List<Order> orders = orderServices.getAll();
                        List<Order> orders1 = new ArrayList<>();

                        for (Order order : orders) {
                            if (Objects.equals(productServices.getProduct(order.getProduct_id()).getWarehouse_id(), wh.getId())) {
                                orders1.add(order);
                            }
                        }

                        for (Order order : orders1) {
                            System.out.println(productServices.getProduct(order.getProduct_id()).getName() + " was purchased at date: " + order.getDate());
                        }
                        break exit;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\nInvalid input!");
                    }
                }
            }
        }

    }
    private void viewCustomers() {
        Scanner scan = new Scanner(System.in);
        exit: {
            while (true) {
                System.out.println("Select user:");
                List<User> users = userServices.getAll();

                for (int i = 0; i < users.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + users.get(i).getUsername());
                }

                int index = 0;
                String n = scan.nextLine();
                if (Objects.equals(n, "x")) {
                    return;
                } else {
                    index = Integer.parseInt(n) - 1;
                }

                try {
                    User user = users.get(index);
                    List<Order> orders = orderServices.getAllOrders(user.getId());
                    for (Order order : orders) {
                        Product product = productServices.getProduct(order.getProduct_id());
                        System.out.println(user.getUsername() + " purchased " + product.getName() + " at this date: " + order.getDate());
                    }
                    break exit;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }



            }
        }
    }

    private void viewWarehouseInventory() {
        Scanner scan = new Scanner(System.in);
        exit: {
            while (true) {
                System.out.println("Select warehouse:");
                List<Warehouse> warehouses = warehouseServices.getAllWarehouses();

                for (int i = 0; i < warehouses.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + warehouses.get(i).getName());
                }

                int index = 0;
                String n = scan.nextLine();
                if (Objects.equals(n, "x")) {
                    return;
                } else {
                    index = Integer.parseInt(n) - 1;
                }

                try {
                    Warehouse wh = warehouses.get(index);
                    List<Product> products = productServices.getByWhID(wh.getId());

                    for (Product product : products) {
                        System.out.println("\n" + product.getName() + " is a " + product.getType() + " made by " + product.getBrand() + ".");
                        System.out.println("There are " + product.getQuantity() + " left in stock.");
                    }
                    break exit;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }



            }
        }
    }


    private void viewProducts() {
        Scanner scan = new Scanner(System.in);
        exit: {
            while (true) {
                System.out.println("\nViewing all products...");
                List<Product> products = productServices.getAllProducts();

                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName());
                }
                System.out.println("[x] Go back");

                System.out.print("\nSelect an item or go back: ");
                int index = 0;
                String n = scan.nextLine();
                if (Objects.equals(n, "x")) {
                    return;
                } else {
                    index = Integer.parseInt(n) - 1;
                }

                try {
                    Product item = products.get(index);

                    System.out.println("\nThe " + item.getName() + " has a quantity of " + item.getQuantity() + ".");
                    System.out.println("\nWould you like to restock this item?");
                    System.out.println("[y] Yes");
                    System.out.println("[x] Go back");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                exitSelect: {
                    while (true) {
                        Product item = products.get(index);
                        String select = scan.nextLine();
                        switch (select) {
                            case "y": {
                                System.out.println("\nItem restocked!");
                                productServices.replenishQuantity(item);
                                return;
                            }
                            case "x": {
                                break exitSelect;
                            }
                        }
                    }
                }

            }

        }
    }
}