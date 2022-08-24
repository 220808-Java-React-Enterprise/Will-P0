package com.revature.saltwater.ui;

import com.revature.saltwater.models.*;
import com.revature.saltwater.services.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class SellerMenu implements IMenu {

    private final Seller seller;
    private final SellerServices sellerServices;
    private final ProductServices productServices;
    private final DeliveryServices deliveryServices;

    public SellerMenu(Seller seller, SellerServices sellerServices, ProductServices productServices, DeliveryServices deliveryServices) {
        this.seller = seller;
        this.sellerServices = sellerServices;
        this.productServices = productServices;
        this.deliveryServices = deliveryServices;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exit: {
            while (true) {
                System.out.println("\nWelcome to the main menu " + seller.getUsername() + "!");
                System.out.println("\nWhat would you like to do?");
                System.out.println("[1] View products we'd like to buy");
                System.out.println("[2] View previous sales");
                System.out.println("[x] Exit");

                switch (scan.nextLine()) {
                    case "1":
                        viewProducts(seller.getId());
                        break;
                    case "2":
                        viewDeliveries(seller.getId());
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

    private void viewProducts(String sellerID) {
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
                    return;
                } else {
                    index = Integer.parseInt(n) - 1;
                }

                try {
                    Product item = products.get(index);

                    System.out.println("\nThe " + item.getName() + " is a " + item.getType() + " made by " + item.getBrand() + ".");
                    System.out.println("We are offering $" + String.valueOf(Integer.parseInt(item.getPrice())*0.6) + " for this item.");
                    System.out.println("\nWould you like to sell this item?");
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
                                System.out.println("\nThank you for selling this item to us! Please send the package to: and we will pay you once it arrives.");
                                deliverItem(item.getId(), sellerID);
                                productServices.addQuantity(item);
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
    private void deliverItem(String itemID, String sellerID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);

        String deliveryID = UUID.randomUUID().toString();
        Delivery delivery = new Delivery(deliveryID, d, sellerID, itemID);
        deliveryServices.saveDelivery(delivery);
    }

    private void viewDeliveries(String sellerID) {
        List<Delivery> deliveries = deliveryServices.getAllDeliveries(sellerID);
        for (int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
            Product product = productServices.getProduct(delivery.getProduct_id());
            System.out.println("\n[" + (i + 1) + "] " + product.getName() + " sold at this date: " + delivery.getDate());
        }
    }


}
