package com.revature.saltwater.services;

import com.revature.saltwater.daos.SellerDAO;
import com.revature.saltwater.daos.UserDAO;
import com.revature.saltwater.models.Seller;
import com.revature.saltwater.models.User;
import com.revature.saltwater.utils.customexceptions.InvalidUserExceptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class SellerServicesTest {

    private SellerServices sut; // sut = system under test
    private final SellerDAO mockSellerDao = mock(SellerDAO.class);

    @Before
    public void setup() {
        sut = new SellerServices(mockSellerDao);
    }

    @Test
    public void test_login_validLoginGivenCorrectCredentials() {
        // Arrange
        SellerServices spiedSut = Mockito.spy(sut);
        String validUsername = "wsmith1234";
        String validPassword = "Passw0rd?";
        when(mockSellerDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new Seller());

        // Act
        Seller seller = spiedSut.login(validUsername, validPassword);

        // Assert
        Assert.assertNotNull(seller);
        verify(mockSellerDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }

    @Test
    public void test_login_invalidLoginGivenCorrectCredentials() {
        // Arrange
        SellerServices spiedSut = Mockito.spy(sut);
        String validUsername = "wsmith1234";
        String validPassword = "Passw0rd?";
        when(mockSellerDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new Seller());

        // Act
        Seller seller = spiedSut.login(validUsername, validPassword);

        // Assert
        Assert.assertNotNull(seller);
        verify(mockSellerDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }

    @Test(expected = InvalidUserExceptions.class)
    public void test_login_invalidLoginGivenIncorrectCredentials() {
        // Arrange
        SellerServices spiedSut = Mockito.spy(sut);
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        when(mockSellerDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);

        // Act
        sut.login(invalidUsername, invalidPassword);
    }

    @Test(expected = InvalidUserExceptions.class)
    public void test_login_invalidLoginGivenIncorrectCredentials2() {
        // Arrange
        SellerServices spiedSut = Mockito.spy(sut);
        String invalidUsername = "";
        String invalidPassword = "";
        when(mockSellerDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);

        // Act
        sut.login(invalidUsername, invalidPassword);
    }
}
