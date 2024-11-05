package com.iffactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagementSystemTest {

    private UserManagementSystem userManagementSystem;

    @Before
    public void setUp() {
        userManagementSystem = new UserManagementSystem();
        userManagementSystem.deleteAllUsers();
    }

    @After
    public void tearDown() {
        userManagementSystem.deleteAllUsers();
        userManagementSystem.closeConnection();
    }

    @Test
    public void testAddUser() {
        userManagementSystem.addUser(1, "test1", "user1", "test1@example.com", 30);
        User user = userManagementSystem.displayUserById(1);
        assertNotNull(user);
        assertEquals("test1", user.getFirstName());
        assertEquals("user1", user.getLastName());
        assertEquals("test1@example.com", user.getEmail());
        assertEquals(30, user.getAge());
    }

    @Test
    public void testRemoveUser() {
        userManagementSystem.addUser(2, "test2", "user2", "test2@example.com", 25);
        userManagementSystem.removeUser(2);
        User user = userManagementSystem.displayUserById(2);
        assertNull(user);
    }

    @Test
    public void testUpdateFirstName() {
        userManagementSystem.addUser(3, "test3", "user3", "test3@example.com", 28);
        userManagementSystem.updateFirstName(3, "test3Updated");
        User user = userManagementSystem.displayUserById(3);
        assertNotNull(user);
        assertEquals("test3Updated", user.getFirstName());
    }

    @Test
    public void testUpdateLastName() {
        userManagementSystem.addUser(4, "test4", "user4", "test4@example.com", 35);
        userManagementSystem.updateLastName(4, "user4Updated");
        User user = userManagementSystem.displayUserById(4);
        assertNotNull(user);
        assertEquals("user4Updated", user.getLastName());
    }

    @Test
    public void testUpdateEmail() {
        userManagementSystem.addUser(5, "test5", "user5", "test5@example.com", 30);
        userManagementSystem.updateEmail(5, "test5updated@example.com");
        User user = userManagementSystem.displayUserById(5);
        assertNotNull(user);
        assertEquals("test5updated@example.com", user.getEmail());
    }

    @Test
    public void testDisplayAllUsers() {
        userManagementSystem.addUser(6, "test6", "user6", "test6@example.com", 40);
        userManagementSystem.addUser(7, "test7", "user7", "test7@example.com", 45);
        userManagementSystem.displayAllUsers(); // This just prints, so we can't assert directly here.
    }

    @Test
    public void testDeleteAllUsers() {
        userManagementSystem.addUser(8, "test8", "user8", "test8@example.com", 20);
        userManagementSystem.addUser(9, "test9", "user9", "test9@example.com", 25);
        userManagementSystem.deleteAllUsers();
        User user1 = userManagementSystem.displayUserById(8);
        User user2 = userManagementSystem.displayUserById(9);
        assertNull(user1);
        assertNull(user2);
    }
}
