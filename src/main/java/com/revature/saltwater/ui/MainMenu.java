package com.revature.saltwater.ui;

import com.revature.saltwater.daos.OrderDAO;
import com.revature.saltwater.daos.ProductDAO;
import com.revature.saltwater.daos.UserDAO;
import com.revature.saltwater.models.Product;
import com.revature.saltwater.models.Order;
import com.revature.saltwater.models.User;
import com.revature.saltwater.services.OrderServices;
import com.revature.saltwater.services.ProductServices;
import com.revature.saltwater.services.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {

    private final User user;
    private final UserServices userServices;
    private final ProductServices productService;
    private final OrderServices orderServices;

    public MainMenu(User user, UserServices userServices, ProductServices productService, OrderServices orderServices) {
        this.user = user;
        this.userServices = userServices;
        this.productService = productService;
        this.orderServices = orderServices;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        List<Product> cart = new ArrayList<>();
        List<String> cartIDs = new ArrayList<>();

        exit: {
            while (true) {
                System.out.println("Welcome to the main menu " + user.getUsername() + "!");
                System.out.println("What would you like to do?");
                System.out.println("[1] View products");
                System.out.println("[2] View cart");
                System.out.println("[3] Buy items in cart");
                System.out.println("[4] View previous orders");
                System.out.println("[x] Exit");

                switch (scan.nextLine()) {
                    case "1":
                        Product item = viewProducts();
                        cart.add(item);
                        cartIDs.add(item.getId());
                        break;
                    case "2":
                        System.out.println(cart);
                        break;
                    case "3":
                        placeOrder(cartIDs, user.getId());
                    case "4":
                        viewOrders(user.getId());
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void viewOrders(String userID) {
        List<Order> orders = orderServices.getAllOrders(userID);
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + orders);
        }
    }

    private void placeOrder(List<String> cartIDs, String userID) {
        for (String id : cartIDs) {
            String orderID = UUID.randomUUID().toString();
            Order order = new Order(orderID, userID, id);
            orderServices.saveOrder(order);
        }

    }


    private Product viewProducts() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nViewing all products...");
                List<Product> products = productService.getAllProducts();

                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName());
                }

                System.out.print("\nSelect an item: ");
                int index = scan.nextInt() - 1;

                try {
                    Product item = products.get(index);

                    System.out.println("The " + item.getName() + " is a " + item.getType() + " made by " + item.getBrand() + ".");
                    System.out.println("It's current price is $" + item.getPrice() + ".");
                    System.out.println("Would you like to add this to your cart?");
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
                                System.out.println("Added to your cart!");
                                return item;
                            }
                        }
                    }
                }
            }

        }
    }
}
