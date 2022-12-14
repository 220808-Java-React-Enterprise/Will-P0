package com.revature.saltwater.services;

import com.revature.saltwater.daos.UserDAO;
import com.revature.saltwater.models.User;
import com.revature.saltwater.utils.customexceptions.InvalidUserExceptions;

import java.util.List;

public class UserServices {

    private final UserDAO userDAO;

    public UserServices(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        User user = userDAO.getUserByUsernameAndPassword(username, password);
        if (user == null) throw new InvalidUserExceptions("\nIncorrect username or password :(");
        return user;
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) throw new InvalidUserExceptions("Invalid username!" + "\nUsername must consist of alphanumeric characters (a-z, A-Z, 0-9), lowercase, or uppercase.\n" + "The dot (.), underscore (_), or hyphen (-) must not be the first or last character and cannot appear consecutively, e.g., java..regex.\n" + "The number of characters must be between 8 to 20.");
        return true;

    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) throw new InvalidUserExceptions(
                "Invalid password!" +
                "\nPassword must contain a minimum of eight characters, at least one uppercase letter, one lowercase letter, one number and one special character");
        return true;
    }


    public void register(User user) {
        userDAO.save(user);
    }

    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null) throw new InvalidUserExceptions("Sorry, " + username + " has already been taken!");
        return false;
    }


}
