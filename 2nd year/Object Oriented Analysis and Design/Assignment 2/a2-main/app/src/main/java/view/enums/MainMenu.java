package view.enums;

import view.interfaces.Describable;

/**
 * This is an enum that holds the main menu options.
 */
public enum MainMenu implements Describable {
  LOGIN("Login user"),
  REGISTER("Register"),
  ADMIN_PANEL("External Admin panel"),
  EXIT("Exit");

  private final String description;

  MainMenu(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
