package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Item.ItemCostPerDay;
import model.Item.ItemDateOfCreation;
import model.Item.ItemName;
import model.Item.ItemShortDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for adding removing and changing items.
 */
public class ItemTest {
  private SystemModel systemModel;
  private User userOne;
  
  /**
   * initialize stater objects.
   */
  @BeforeEach
  public void setUp() {
    systemModel = new SystemModel();
    userOne = systemModel.addUser("User1", "user@example.com", "1234567890", "1234");
    systemModel.authenticateUser("user@example.com", "1234");
  }

  @Test
  public void testAddItemUser() {
    Item item = new Item(new ItemName("Phone"), new ItemShortDescription("A Phone"),
        new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("5.00"),
        Item.Category.Toy, userOne);
    Item addedItem = systemModel.addItemUser(item, userOne);

    assertNotNull(addedItem);
    assertEquals(userOne.getMemberId(), addedItem.getOwnerId());
    assertEquals(100.0, userOne.getCredits(), 0.01);
    assertEquals(1, userOne.getItemCount());

    assertTrue(itemExists(addedItem));
  }

  @Test
  public void testDeleteItemUser() {
    Item item = new Item(new ItemName("Phone"), new ItemShortDescription("A Phone"),
        new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("5.00"),
        Item.Category.Toy, userOne);
    systemModel.addItemUser(item, userOne);

    boolean deleted = systemModel.deleteItemUser(item.getName().asString());

    assertTrue(deleted);
    assertEquals(0, userOne.getItemCount());
    assertFalse(itemExists(item));
  }

  @Test
  public void testReplaceItem() {
    Item item1 = new Item(new ItemName("Phone"), new ItemShortDescription("A Phone"),
        new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("5.00"),
        Item.Category.Toy, userOne);

    Item item2 = new Item(new ItemName("PhoneNew"), new ItemShortDescription("A new Phone"),
        new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("50.00"),
        Item.Category.Other, userOne);
    systemModel.addItemUser(item1, userOne);
    systemModel.addItemUser(item2, userOne);

    systemModel.replaceItem(item1, item2);

    assertFalse(itemExists(item1));
    assertTrue(itemExists(item2));
  }

  private boolean itemExists(Item item) {
    boolean itemIsAvailable = false;
    Iterable<Item> items = systemModel.getAllItems();

    for (Item i : items) {
      if (i.getNameAsString().equals(item.getNameAsString())) {
        itemIsAvailable = true;
      }
    }

    return itemIsAvailable;
  }
}
