package com.revature.saltwater.ui;

import com.revature.saltwater.models.User;

public class MainMenu implements IMenu {

    private final User user;

    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("Welcome to the main menu " + user.getUsername() + "!");

    }
}
