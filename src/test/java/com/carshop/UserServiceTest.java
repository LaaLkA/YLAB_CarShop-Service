package com.carshop;

import com.carshop.model.User;
import com.carshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testRegister() {
        assertTrue(userService.register("user1", "pass", "client"));
        assertFalse(userService.register("user1", "pass", "client"));
    }

    @Test
    public void testLogin() {
        userService.register("user1", "pass", "client");
        assertNotNull(userService.login("user1", "pass"));
        assertNull(userService.login("user1", "wrongpass"));
    }

    @Test
    public void testListUsers() {
        userService.register("user1", "pass", "client");
        assertEquals(1, userService.listUsers().size());
    }

    @Test
    public void testAddEditEmployee() {
        userService.addEmployee("employee1", "pass", "manager");
        assertNotNull(userService.login("employee1", "pass"));
        userService.editEmployee("employee1", "newpass", "admin");
        assertNull(userService.login("employee1", "pass"));
        assertNotNull(userService.login("employee1", "newpass"));
    }
}
