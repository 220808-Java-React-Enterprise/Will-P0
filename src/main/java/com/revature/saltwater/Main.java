package com.revature.saltwater;

import com.revature.saltwater.daos.UserDAO;
import com.revature.saltwater.services.UserServices;
import com.revature.saltwater.ui.LoginMenu;
import com.revature.saltwater.utils.database.ConnectionFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        UserServices userServices = new UserServices(userDAO);
        new LoginMenu(new UserServices(new UserDAO())).start();


        try {
            System.out.println(ConnectionFactory.getInstance().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
