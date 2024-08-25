package view.enums;

import view.interfaces.Describable;

/**
 * Enum for profile page options.
 */
public enum ProfileOptions implements Describable {
  
  VIEW("View profile data"),
  UPDATE("Update profile"),
  DELETE("Delete profile"),
  EXIT("Exit");

  private final String description;

  ProfileOptions(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
