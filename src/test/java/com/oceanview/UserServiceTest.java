package com.oceanview;

import com.oceanview.model.User;
import com.oceanview.service.UserService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// Test class for UserService
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testUserServiceNotNull() {
        assertNotNull("UserService should not be null", userService);
    }

    @Test
    public void testGetAllUsers() {
        assertNotNull("getAllUsers should not return null", userService.getAllUsers());
    }
}
