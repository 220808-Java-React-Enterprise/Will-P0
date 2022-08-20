package com.revature.saltwater.ui;

import com.revature.saltwater.models.User;
import com.revature.saltwater.services.UserServices;
import com.revature.saltwater.utils.customexceptions.InvalidUserExceptions;

import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements IMenu {

    private final UserServices userServices;

    public LoginMenu(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public void start() {

        String userIn = "";
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to SaltWater!");
                System.out.println("[1] Login");
                System.out.println("[2] Sign Up");
                System.out.println("[3] Funny Quote");
                System.out.println("[x] Exit");

                System.out.println("\nEnter: ");
                userIn = scan.nextLine().toLowerCase();

                switch (userIn) {
                    case "1":
                        login();
                        break exit;
                    case "2":
                        User user = signUp();
                        userServices.register(user);
                        System.out.println("Successfully created account!");
                        break;
                    case "3":
                        break;
                    case "x":
                        System.out.println("See ya!");
                        break exit;
                    default:
                        System.out.println("\nLol you tried!");
                        break;
                }
            }
        }


    }
    private User signUp() {
        String username = "";
        String password = "";
        boolean buyer = false;
        boolean seller = false;

        User user = new User();
        System.out.println("Before we create your account we will need a few details.");
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                exitUN: {
                    while (true) {
                        System.out.println("Create a username:");
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
                        System.out.println("Create a password:");
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
                        System.out.println("Are you looking to buy or sell?");
                        System.out.println("[b] Buy");
                        System.out.println("[s] Sell");
                        String userRole = "";
                        userRole = scan.nextLine().toLowerCase();
                        switch (userRole) {
                            case "s":
                                seller = true;
                                break exitRole;
                            case "b":
                                buyer = true;
                                break exitRole;
                            default:
                                System.out.println("Invalid input!");
                                break;
                        }
                    }
                }

                String role = "";
                if (seller) {
                    role = "Seller";
                } else {
                    role = "Buyer";
                }

                exitCorrect: {
                    while (true) {
                        System.out.println("Here is what we gathered:");
                        System.out.println("Username: " + username + "\nPassword: " + password + "\nRole: " + role);
                        System.out.println("Does this look correct?");
                        System.out.println("[y] Yes");
                        System.out.println("[n] No");
                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                user = new User(UUID.randomUUID().toString(), username, password, buyer, seller);
                                return user;
                            case "n":
                                System.out.println("Let's try again!");
                                break exitCorrect;
                            default:
                                System.out.println("Invalid input!");
                                break;
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
                    new MainMenu(user).start();
                    break exit;
                } catch (InvalidUserExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
