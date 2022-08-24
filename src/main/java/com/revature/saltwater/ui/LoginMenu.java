package com.revature.saltwater.ui;

import com.revature.saltwater.daos.*;
import com.revature.saltwater.models.Product;
import com.revature.saltwater.models.Seller;
import com.revature.saltwater.models.User;
import com.revature.saltwater.services.*;
import com.revature.saltwater.utils.customexceptions.InvalidUserExceptions;

import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements IMenu {

    private final UserServices userServices;
    private final SellerServices sellerServices;

    public LoginMenu(UserServices userServices, SellerServices sellerServices) {
        this.userServices = userServices;
        this.sellerServices = sellerServices;
    }

    @Override
    public void start() {

        String userIn = "";
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to SaltWater!\n");
                System.out.println("[1] Buyer Login");
                System.out.println("[2] Seller Login");
                System.out.println("[3] Sign Up");
                System.out.println("[x] Exit");

                System.out.println("\nEnter: ");
                userIn = scan.nextLine().toLowerCase();

                switch (userIn) {
                    case "1":
                        login();
                        break exit;
                    case "2":
                        sellerLogin();
                        break exit;
                    case "3":
                        signUp();
                        break;
                    case "x":
                        System.out.println("See ya!");
                        break exit;
                    case "secretadminlogin220808":
                        new AdminMenu(new ProductServices(new ProductDAO()), new WarehouseServices(new WarehouseDAO()), new UserServices(new UserDAO()), new OrderServices(new OrderDAO())).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }


    }
    private void signUp() {
        String username = "";
        String password = "";
        boolean buyer = false;
        boolean sell = false;

        User user = new User();
        Seller seller = new Seller();
        System.out.println("Before we create your account we will need a few details.");
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                exitUN: {
                    while (true) {
                        System.out.println("\nCreate a username:");
                        username = scan.nextLine();

                        try {
                            userServices.isValidUsername(username);
                            userServices.isDuplicateUsername(username);
                            break exitUN;
                        } catch (InvalidUserExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                exitPW: {
                    while (true) {
                        System.out.println("\nCreate a password:");
                        password = scan.nextLine();

                        try {
                            userServices.isValidPassword(password);
                            break exitPW;
                        } catch (InvalidUserExceptions e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                exitRole: {
                    while (true) {
                        System.out.println("\nAre you looking to buy or sell?");
                        System.out.println("[b] Buy");
                        System.out.println("[s] Sell");
                        String userRole = "";
                        userRole = scan.nextLine().toLowerCase();
                        switch (userRole) {
                            case "s":
                                sell = true;
                                break exitRole;
                            case "b":
                                buyer = true;
                                break exitRole;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }
                    }
                }

                String role = "";
                if (sell) {
                    role = "Seller";
                    exitCorrect: {
                        while (true) {
                            System.out.println("\nHere is what we gathered:");
                            System.out.println("Username: " + username + "\nPassword: " + password + "\nRole: " + role);
                            System.out.println("\nDoes this look correct?");
                            System.out.println("[y] Yes");
                            System.out.println("[n] No");
                            switch (scan.nextLine().toLowerCase()) {
                                case "y":
                                    seller = new Seller(UUID.randomUUID().toString(), username, password);
                                    sellerServices.register(seller);
                                    System.out.println("\nSuccessfully created account!");
                                    return;
                                case "n":
                                    System.out.println("\nLet's try again!");
                                    break exitCorrect;
                                default:
                                    System.out.println("\nInvalid input!");
                                    break;
                            }
                        }
                    }
                } else {
                    role = "Buyer";
                    exitCorrect: {
                        while (true) {
                            System.out.println("\nHere is what we gathered:");
                            System.out.println("Username: " + username + "\nPassword: " + password + "\nRole: " + role);
                            System.out.println("\nDoes this look correct?");
                            System.out.println("[y] Yes");
                            System.out.println("[n] No");
                            switch (scan.nextLine().toLowerCase()) {
                                case "y":
                                    user = new User(UUID.randomUUID().toString(), username, password);
                                    userServices.register(user);
                                    System.out.println("\nSuccessfully created account!");
                                    return;
                                case "n":
                                    System.out.println("\nLet's try again!");
                                    break exitCorrect;
                                default:
                                    System.out.println("\nInvalid input!");
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    User user = userServices.login(username, password);
                    new MainMenu(user, new UserServices(new UserDAO()), new ProductServices(new ProductDAO()), new OrderServices(new OrderDAO())).start();
                    break exit;
                } catch (InvalidUserExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void sellerLogin() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    Seller seller = sellerServices.login(username, password);
                    new SellerMenu(seller, new SellerServices(new SellerDAO()), new ProductServices(new ProductDAO()), new DeliveryServices(new DeliveryDAO())).start();
                    break exit;
                } catch (InvalidUserExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
