package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import model.Item.ItemCostPerDay;
import model.Item.ItemDateOfCreation;
import model.Item.ItemName;
import model.Item.ItemShortDescription;
import model.interfaces.UserOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test to add remove and update users.
 */
public class UserTests {

  private SystemModel systemModel;

  @BeforeEach
  public void setUp() {
    systemModel = new SystemModel();
  }

  @Test
  public void testAddUser() {
    // Test adding a new user
    User newUser = systemModel.addUser("John Doe", "johndoe@example.com", "1234567890", "password123");
    assertNotNull(newUser);
    assertEquals("John Doe", newUser.getName());
    assertEquals("johndoe@example.com", newUser.getEmail());
    assertEquals("1234567890", newUser.getPhoneNumber());

    // Test adding a user object
    User existingUser = new User("Alice Smith", "alice@example.com", "9876543210", "password456");
    User addedUser = systemModel.addUser(existingUser);
    assertNotNull(addedUser);
    assertEquals("Alice Smith", addedUser.getName());
    assertEquals("alice@example.com", addedUser.getEmail());
    assertEquals("9876543210", addedUser.getPhoneNumber());
  }

  @Test
  public void testRemoveUser() {
    // Add a user
    User userToRemove = systemModel.addUser("John Doe", "johndoe@example.com", "1234567890", "password123");

    Item item = new Item(new ItemName("Phone"), new ItemShortDescription("A Phone"),
        new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("5.00"),
        Item.Category.Toy, userToRemove);
    systemModel.addItemUser(item, userToRemove);

    // Authenticate the user
    systemModel.authenticateUser("johndoe@example.com", "password123");

    // Test removing the logged-in user
    systemModel.removeUser();

    // Check if the user has been removed from the users ArrayList
    boolean userIsAvailable = false;
    Iterable<UserOperations> users = systemModel.getAllUsers();

    for (UserOperations u : users) {
      if (u == userToRemove) {
        userIsAvailable = true;
      }
    }

    // check if the item is removed
    boolean itemIsAvailable = false;
    Iterable<Item> items = systemModel.getAllItems();

    for (Item i : items) {
      if (i.getOwnerId().equals(item.getOwnerId())) {
        itemIsAvailable = true;
      }
    }

    assertFalse(userIsAvailable);
    assertFalse(itemIsAvailable);
  }

  @Test
  public void testUpdateUserName() {
    systemModel.addUser("John Doe", "johndoe@example.com", "1234567890", "password123");
    systemModel.authenticateUser("johndoe@example.com", "password123");

    // Test updating the user's name
    systemModel.updateUserName("New Name");
    assertEquals("New Name", systemModel.getLoggedInUser().getName());
  }

  @Test
  public void testUpdateUserPhoneNumber() {
    systemModel.addUser("John Doe", "johndoe@example.com", "1234567890", "password123");
    systemModel.authenticateUser("johndoe@example.com", "password123");

    // Test updating the user's phone number
    systemModel.updateUserPhoneNumber("9876543210");
    assertEquals("9876543210", systemModel.getLoggedInUser().getPhoneNumber());
  }

  @Test
  public void testUpdateUserEmail() {
    systemModel.addUser("John Doe", "johndoe@example.com", "1234567890", "password123");
    systemModel.authenticateUser("johndoe@example.com", "password123");

    // Test updating the user's email
    systemModel.updateUserEmail("newemail@example.com");
    assertEquals("newemail@example.com", systemModel.getLoggedInUser().getEmail());
  }

  @Test
  public void testUpdateUserPassword() {
    systemModel.addUser("John Doe", "johndoe@example.com", "1234567890", "password123");
    systemModel.authenticateUser("johndoe@example.com", "password123");

    // Test updating the user's password
    systemModel.updateUserPassword("newPassword456");
    assertEquals("newPassword456", systemModel.getLoggedInUser().getPassword());
  }

  @Test
  public void testAddUserWithSameEmail() {

    try {
      systemModel.addUser("User 1", "user@example.com", "1234567890", "password1");

      systemModel.addUser("User 2", "user@example.com", "9876543210", "password2");

      // If the method doesn't throw the exception, fail the test
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // The exception was thrown as expected
    }
  }

  @Test
  public void testAddUserWithSamePhoneNumber() {
    try {
      systemModel.addUser("User 1", "user@example.com", "1234567890", "password1");

      systemModel.addUser("User 2", "user1@example.com", "1234567890", "password2");

      // If the method doesn't throw the exception, fail the test
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // The exception was thrown as expected
    }
  }

}
