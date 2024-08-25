package view.enums;

import view.interfaces.Describable;

/**
 * This is an enum that holds logged in users menu.
 */
public enum UserPage implements Describable {
  PROFILE_PAGE("Profile Page"),
  ITEM_MENU("Your inventory"),
  BORROW("Borrow an item"),
  LIST_SYSTEM("List all items and their contracts"),
  PURCHASE("Purchase credits"),
  EXIT("Logout");

  private final String description;

  UserPage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
