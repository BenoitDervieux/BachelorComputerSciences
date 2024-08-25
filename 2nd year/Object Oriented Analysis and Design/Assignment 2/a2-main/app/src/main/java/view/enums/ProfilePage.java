package view.enums;

import view.interfaces.Describable;

/**
 * This is an enum that holds profile page options.
 */
public enum ProfilePage implements Describable {
  UPDATE_USER_NAME("Update username"), 
  UPDATE_EMAIL("Update email"), 
  UPDATE_PHONE("Update phone number"),
  UPDATE_PASSWORD("Update password"), 
  EXIT("Exit");

  private final String description;

  ProfilePage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
