package model;

import model.Item.ItemCostPerDay;
import model.Item.ItemDateOfCreation;
import model.Item.ItemName;
import model.Item.ItemShortDescription;
import model.interfaces.SystemModelOperations;

/**
 * Load a set of hard coded data.
 */
public class HardCode {
  User userOne = new User("Jack", "jack@gmail.com", "072000001", "1234", 500);

  Item itemOne = new Item(new ItemName("Phone"), new ItemShortDescription("A Phone"),
      new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("15.00"),
      Item.Category.Toy, userOne);

  Item itemTwo = new Item(new ItemName("Bottle"), new ItemShortDescription("test"),
      new ItemDateOfCreation("2023/09/22"), new ItemCostPerDay("5.00"),
      Item.Category.Tool, userOne);

  Item itemThree = new Item(new ItemName("Hat"), new ItemShortDescription("test"),
      new ItemDateOfCreation("2023/09/22"), new ItemCostPerDay("2.00"),
      Item.Category.Other, userOne);

  User userTwo = new User("Emilie", "emilie@gmail.com", "072000002", "1234", 100);

  Item itemFour = new Item(new ItemName("Ball"), new ItemShortDescription("A Ball"),
      new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("5.00"),
      Item.Category.Toy, userTwo);

  Item itemFive = new Item(new ItemName("Bat"), new ItemShortDescription("A Bat"),
      new ItemDateOfCreation("2023/09/23"), new ItemCostPerDay("10.00"),
      Item.Category.Toy, userTwo);

  Item itemSix = new Item(new ItemName("Hokey Puck"), new ItemShortDescription("A Hokey Puck"),
      new ItemDateOfCreation("2023/09/22"), new ItemCostPerDay("10.00"),
      Item.Category.Toy, userTwo);

  /**
   * Load hardcoded users.
   *
   * @param sys system model.
   */
  public void loadData(SystemModelOperations sys) {
    User[] members = { userOne, userTwo };
    User userForContract = userTwo;

    for (User u : members) {
      userForContract = sys.addUser(u);
    }

    // items for user one
    Item addedItemOne = sys.addItemUser(itemOne, userOne);
    // contract for that item
    createContract(sys, userForContract, addedItemOne, "2023/10/20", "2023/10/22");
    sys.addItemUser(itemTwo, userOne);
    sys.addItemUser(itemThree, userOne);

    // items for user two
    sys.addItemUser(itemFour, userTwo);
    sys.addItemUser(itemFive, userTwo);
    sys.addItemUser(itemSix, userTwo);

    User m1 = new User("m1", "m1@gmail.com", "1111", "1234", 500);
    Item i1 = new Item(new ItemName("I1"), new ItemShortDescription("item 1"),
        new ItemDateOfCreation("2023/09/22"), new ItemCostPerDay("50.00"),
        Item.Category.Toy, m1);
    Item i2 = new Item(new ItemName("I2"), new ItemShortDescription("Item 2"),
        new ItemDateOfCreation("2023/09/22"), new ItemCostPerDay("10.00"),
        Item.Category.Toy, m1);
    User m2 = new User("m2", "m2@gmail.com", "2222", "1234", 100);
    User m3 = new User("m3", "m3@gmail.com", "3333", "1234", 100);

    sys.addUser(m1);
    sys.addUser(m2);
    sys.addUser(m3);
    sys.addItemUser(i1, m1);
    Item i2instance = sys.addItemUser(i2, m1);
    createContract(sys, m3, i2instance, "2023/10/13", "2023/10/15");

  }

  /**
   * Creates a s dummy contract.
   */
  public void createContract(SystemModelOperations sys,
      User user, Item item, String startDate, String endDate) {
    sys.addContractToUser(item, startDate,
        endDate, user);
  }
}

