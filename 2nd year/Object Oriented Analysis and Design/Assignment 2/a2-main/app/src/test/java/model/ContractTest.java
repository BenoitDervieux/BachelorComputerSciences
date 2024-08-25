package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for creating contracts.
 */
public class ContractTest {

  private SystemModel systemModel;
  private User user;
  private User userTwo;
  private Item item;
  private Item itemTwo;

  /**
   * initializing objects required.
   */
  @BeforeEach
  public void setUp() {
    systemModel = new SystemModel();
    user = systemModel.addUser("John", "john@example.com", "1234567890", "1234");
    userTwo = systemModel.addUser("Doe", "doe@example.com", "092938392", "1234");
    systemModel.authenticateUser("john@example.com", "1234");

    item = new Item(new Item.ItemName("Tool1"), new Item.ItemShortDescription("Description"),
        new Item.ItemDateOfCreation("2023/10/01"), new Item.ItemCostPerDay("5.0"),
        Item.Category.Tool, user);
    systemModel.authenticateUser("john@example.com", "1234");
    systemModel.addItemUser(item, user);

    itemTwo = new Item(new Item.ItemName("Tool2"), new Item.ItemShortDescription("Description"),
        new Item.ItemDateOfCreation("2023/10/01"), new Item.ItemCostPerDay("5.0"),
        Item.Category.Tool, userTwo);

  }

  @Test
  public void testAddContractToUser() {
    Contract contract = systemModel.addContractToUser(itemTwo, "2023/10/10", "2023/10/14", user);
    assertNotNull(contract);
    assertTrue(user.getCredits() == 75); // Verify that user credits were updated
    assertEquals(contract.getStartDate(), "2023/10/10");
    assertEquals(contract.getEndDate(), "2023/10/14");
    assertEquals(contract.getLenderId(), userTwo.getMemberId());
    assertEquals(contract.getBorrowerId(), user.getMemberId());
  }

  @Test
  public void testAddContractToUserInsufficientCredits() {
    // Set user's credits to less than the contract cost
    user.decrementCredits(100);
    try {
      systemModel.addContractToUser(item, "2023/10/10", "2023/10/15", user);
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // Expect an IllegalArgumentException to be thrown
    }
  }

  @Test
  public void testAddContractToUserInvalidStartDate() {
    systemModel.addContractToUser(item, "2023/01/10", "2023/01/03", user);
  }

  @Test
  public void testAddContractToUserOverlappingStartDates() {
    // Create an initial contract for the item
    systemModel.addContractToUser(item, "2023/10/10", "2023/10/15", user);

    try {
      // Try to add another contract with overlapping dates
      systemModel.addContractToUser(item, "2023/10/12", "2023/10/18", user);
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // Expect an IllegalArgumentException to be thrown
    }
  }

  @Test
  public void testAddContractToUserOverlappingEndDates() {
    // Create an initial contract for the item
    systemModel.addContractToUser(item, "2023/10/20", "2023/10/25", user);

    try {
      // Try to add another contract with overlapping dates
      systemModel.addContractToUser(item, "2023/10/13", "2023/10/21", user);
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // Expect an IllegalArgumentException to be thrown
    }
  }

    @Test
  public void testAddContractToUserOverlappingDatesWithin() {
    // Create an initial contract for the item
    systemModel.addContractToUser(item, "2023/10/20", "2023/10/22", user);

    try {
      // Try to add another contract with overlapping dates
      systemModel.addContractToUser(item, "2023/10/19", "2023/10/23", user);
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // Expect an IllegalArgumentException to be thrown
    }
  }

    @Test
  public void testAddContractToUserOverlappingDates() {
    // Create an initial contract for the item
    systemModel.addContractToUser(item, "2023/10/18", "2023/10/25", user);

    try {
      // Try to add another contract with overlapping dates
      systemModel.addContractToUser(item, "2023/10/19", "2023/10/23", user);
      fail("Expected IllegalArgumentException, but no exception was thrown.");
    } catch (IllegalArgumentException expectedException) {
      // Expect an IllegalArgumentException to be thrown
    }
  }


}
