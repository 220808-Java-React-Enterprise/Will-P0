package com.revature.saltwater.services;

import com.revature.saltwater.daos.SellerDAO;
import com.revature.saltwater.models.Seller;
import com.revature.saltwater.models.User;
import com.revature.saltwater.utils.customexceptions.InvalidUserExceptions;

public class SellerServices {

    private final SellerDAO sellerDAO;

    public SellerServices(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }

    public Seller login(String username, String password) {
        Seller seller = sellerDAO.getUserByUsernameAndPassword(username, password);
        if (seller == null) throw new InvalidUserExceptions("\nIncorrect username or password :(");
        return seller;
    }

    public void register(Seller seller) {
        sellerDAO.save(seller);
    }

}
