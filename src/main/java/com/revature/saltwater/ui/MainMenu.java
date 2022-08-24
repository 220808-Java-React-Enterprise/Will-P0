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

import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu implements IMenu {

    private final User user;
    private final UserServices userServices;
    private final ProductServices productServices;
    private final OrderServices orderServices;

    public MainMenu(User user, UserServices userServices, ProductServices productServices, OrderServices orderServices) {
        this.user = user;
        this.userServices = userServices;
        this.productServices = productServices;
        this.orderServices = orderServices;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        List<Product> cart = new ArrayList<>();
        List<String> cartIDs = new ArrayList<>();
        int cartTotal = 0;

        exit: {
            while (true) {
                System.out.println("\nWelcome to the main menu " + user.getUsername() + "!");
                System.out.println("\nWhat would you like to do?");
                System.out.println("[1] View products");
                System.out.println("[2] View cart");
                System.out.println("[3] Buy items in cart");
                System.out.println("[4] View previous orders");
                System.out.println("[x] Exit");

                switch (scan.nextLine()) {
                    case "1":
                        Product item = viewProducts();
                        if (item == null) {
                            break;
                        }
                        cart.add(item);
                        cartIDs.add(item.getId());
                        cartTotal += Integer.parseInt(item.getPrice());
                        break;
                    case "2":
                        System.out.println("\nHere are your selected items for a total of $" + cartTotal + ".");
                        System.out.println(cart);
                        break;
                    case "3":
                        placeOrder(cartIDs, user.getId());
                        cart.clear();
                        cartIDs.clear();
                        cartTotal -= cartTotal;
                        break;
                    case "4":
                        viewOrders(user.getId());
                        break;
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
            Order order = orders.get(i);
            Product product = productServices.getProduct(order.getProduct_id());
            System.out.println("\n[" + (i + 1) + "] " + product.getName() + " purchased at this date: " + order.getDate());
        }
    }

    private void placeOrder(List<String> cartIDs, String userID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        for (String id : cartIDs) {
            Product prod = productServices.getProduct(id);
            productServices.subtractQuantity(prod);
            String orderID = UUID.randomUUID().toString();
            Order order = new Order(orderID, d, userID, id);
            orderServices.saveOrder(order);
        }

    }


    private Product viewProducts() {
        Scanner scan = new Scanner(System.in);
        exit:
        {
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
                    return null;
                } else {
                    index = Integer.parseInt(n) - 1;
                }

                try {
                    Product item = products.get(index);

                    System.out.println("\nThe " + item.getName() + " is a " + item.getType() + " made by " + item.getBrand() + ".");
                    System.out.println("It's current price is $" + item.getPrice() + ".");
                    System.out.println("\nWould you like to add this to your cart?");
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
                                System.out.println("\nAdded to your cart!");
                                return item;
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
