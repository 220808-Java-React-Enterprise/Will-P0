package com.revature.saltwater.services;

import com.revature.saltwater.daos.UserDAO;
import com.revature.saltwater.models.User;
import com.revature.saltwater.utils.customexceptions.InvalidUserExceptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServicesTest {

    private UserServices sut; // sut = system under test
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserServices(mockUserDao);
    }

    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        // Arrange
        String validUsername = "wsmith_1999";

        // Act
        boolean flag = sut.isValidUsername(validUsername);

        // Assert
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidUserExceptions.class)
    public void test_isNotValidUsername_givenInCorrectUsername() {
        // Arrange
        String invalidUsername = "wsmith";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidUserExceptions.class)
    public void test_isNotValidUsername_givenEmptyUsername() {
        // Arrange
        String invalidUsername = "";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test
    public void test_login_validLoginGivenCorrectCredentials() {
        // Arrange
        UserServices spiedSut = Mockito.spy(sut);
        String validUsername = "wsmith1234";
        String validPassword = "Passw0rd?";
        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new User());

        // Act
        User user = spiedSut.login(validUsername, validPassword);

        // Assert
        Assert.assertNotNull(user);
        verify(mockUserDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }

}
