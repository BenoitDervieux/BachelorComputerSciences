package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Contract;
import model.Item;
import model.Item.ItemDateOfCreation;

/**
 * Class to handle item viewing behavior.
 */
public class ItemMenuView {

  ConsolePromptItem prompt = new ConsolePromptItem(str -> this.print(str), str -> this.prompt(str));

  /**
   * Enum to hold different item catagories.
   */
  public static enum Category {
    Tool,
    Vehicle,
    Game,
    Toy,
    Sport,
    Other
  }

  /**
   * Enum to hold Item menu options.
   */
  public static enum ItemMenuAction {
    None,
    CREATE_item,
    SHOW_item,
    DELETE_item,
    UPDATE_item,
    EXIT
  }

  /**
   * Gets the menu selection.
   *
   * @return selected action.
   */
  public ItemMenuAction showItemMenuAction() {
    ItemMenuAction actions = ItemMenuAction.None;

    while (actions == ItemMenuAction.None) {
      printItemMenu();
      actions = toItemMenuAction(readLine());
      if (actions == ItemMenuAction.None) {
        print("Invalid input. Try again.\n");
      }
    }
    return actions;
  }

  private void print(String text) {
    System.out.println(text);
  }

  private void printItemMenu() {
    print("Item menu");
    print("1. Create new item");
    print("2. Show your items");
    print("3. Delete item");
    print("4. Update item");
    print("Q. Exit\n");
    print("Enter your choice: ");
  }

  protected ItemMenuAction toItemMenuAction(String input) {
    if (input.length() != 1) {
      return ItemMenuAction.None;
    }
    switch (input.charAt(0)) {
      case '1':
        return ItemMenuAction.CREATE_item;
      case '2':
        return ItemMenuAction.SHOW_item;
      case '3':
        return ItemMenuAction.DELETE_item;
      case '4':
        return ItemMenuAction.UPDATE_item;
      case 'q', 'Q':
        return ItemMenuAction.EXIT;
      default:
        return ItemMenuAction.None;
    }
  }

  protected String readLine() {
    try {
      var reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  /**
   * Prints a list of items to the console.
   *
   * @param items the list of items.
   */
  public void printItems(Iterable<model.Item> items) {
    print("");
    print("Items:");
    for (model.Item i : items) {
      printItemName(i, "");
      printItemDescription(i, "");
      printItemDayOfCreation(i, "");
      printItemCostPerDay(i, "");
      printItemCategory(i, "");
      System.out.println("");
    }
    print("");
  }

  /**
   * Prints a list of items to the console.
   *
   * @param itemsAndContract the list of items and contracts.
   */
  public void printItemsAndContract(Iterable<Object> itemsAndContract) {
    print("");
    print("Items:");
    for (Object o : itemsAndContract) {
      if (o instanceof Item) {
        printItemName((Item) o, "");
        printItemDescription((Item) o, "");
        printItemDayOfCreation((Item) o, "");
        printItemCostPerDay((Item) o, "");
        printItemCategory((Item) o, "");
        System.out.println("");
      } else if (o instanceof Contract) {
        System.out.print("    ");
        printContract((Contract) o);
      }
    }
    print("");
  }

  public void printItemName(model.Item item, String message) {
    System.out.println("Name: " + item.getName().asString() + message);
  }

  public void printItemDescription(model.Item item, String message) {
    System.out.println("Short Description: " + item.getShortDescription().asString() + message);
  }

  public void printItemDayOfCreation(model.Item item, String message) {
    System.out.println("Date of creation: " + item.getDayOfCreation().asString() + message);
  }

  public void printItemCostPerDay(model.Item item, String message) {
    System.out.println("Cost per day: " + item.getCostPerDay().asString() + message);
  }

  public void printItemCategory(model.Item item, String message) {
    System.out.println("Category: " + item.getCategory() + message);
  }


  /**
   * Print the item.

   * @param item is the item.
   * @param message is the message to display.
   */
  public void printItem(model.Item item, String message) {
    System.out.println(item.getName().asString() 
        + ", " + item.getShortDescription().asString()
        + message);
    // To implement more later on
  }

  /**
   * Shows item added message.
   *
   * @param item the item
   */
  public void printItemAdded(model.Item item) {
    print("");
    printItemName(item, " added");
    print("");
  }

  /**
   * Shows item add failure message.
   *
   * @param item the item.
   */
  public void printItemAddedFailed(model.Item item) {
    print("");
    printItem(item, " NOT added");
    print("");
  }

  /**
   * Get details to add a new item.
   *
   * @return added item.
   */
  public model.Item getItemToAdd() {
    print("");
    print("Add a new item");
    print("(leave any field empty and press enter to cancel)");
    var name = prompt.getItemName();
    if (name == null) {
      return null;
    }
    var shortDescription = prompt.getItemShortDescription();
    if (shortDescription == null) {
      return null;
    }
    var costPerDay = prompt.getItemCostPerDay();
    if (costPerDay == null) {
      return null;
    }
    var category = prompt.getItemCategory();
    if (category == null) {
      return null;
    }
    var dateRegistration = new model.Item.ItemDateOfCreation(
        new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
    return new Item(name, shortDescription, dateRegistration, costPerDay, category);
  }

  /**
   * Ask the user the name of the item to get.

   * @return the name of the item as a string.
   */
  public String askForItemUpdate() {
    print("");
    print("Which item do you want to update");
    print("(leave any field empty and press enter to cancel)");
    var name = prompt.getItemName();
    if (name == null) {
      return null;
    }
    return name.asString();
  }

  /**
   * Get details to add a new item.
   *
   * @return added item.
   */
  public model.Item updateItemInformation(ItemDateOfCreation date, Item previous) {
    print("");
    print("You have decided to update this item");
    printItem(previous, "");
    print("(leave any field empty and press enter to cancel)");
    var name = prompt.getItemName();
    if (name == null) {
      return null;
    }
    var shortDescription = prompt.getItemShortDescription();
    if (shortDescription == null) {
      return null;
    }
    var costPerDay = prompt.getItemCostPerDay();
    if (costPerDay == null) {
      return null;
    }
    var category = prompt.getItemCategory();
    if (category == null) {
      return null;
    }
    return new Item(name, shortDescription, date, costPerDay, category);
  }

  /**
   * Find item to remove.
   *
   * @return removed item.
   */
  public String getItemToRemove() {
    print("");
    print("Which item would you like to delete?\n");
    var name = prompt.getItemName();
    if (name == null) {
      return null;
    }
    return name.asString();
  }

  private String prompt(String prompt) {
    print(prompt);
    return readLine();
  }

  /**
   * Print a message that a string has been deleted.

   * @param i is a string.
   */
  public void messageDeletionItem(String i) {
    System.out.println(i + " has been deleted with success\n");
  }

  /**
   * Print a message that a string has not been deleted.

   * @param i is a string.
   */
  public void messageNotDeletionItem(String i) {
    System.out.println(i 
        + " has not been deleted and appear to not be"
        + "an item or it may have an active contract\n");
  }

  /**
   * Print a message that an Item doesn't exist.

   * @param i is a string.
   */
  public void messageItemNotExisting(String i) {
    System.out.println(i + "Does not seem to be an item present in the system\n");
  }

  /**
   * Tells the user you do not own this item.
   */
  public void printNotOwnership() {
    System.out.println("You do not own this item\n");
  }

  /**
   * Tells the user adding the item has failed.
   */
  public void itemAddFail() {
    System.out.println("\nThe system failed to add the item\n");
  }

  /**
   * Print a contract.
   *
   * @param c contracts
   */
  public void printContract(Contract c) {
    StringBuilder str = new StringBuilder();

    str.append("\nContract made between " + c.getLender().getName()
        + " and " + c.getBorrower().getName() + " for a "
        + c.getItem().getNameAsString() + " from " + c.getStartDate()
        + " till " + c.getEndDate() + " for a period of " + c.getLength()
        + " days \n" + "Current status of the contact is :- expired: " + c.getIsExpired()
        + " Active : " + c.getIsActive() + "\n");

    System.out.println(str.toString());
  }

}
