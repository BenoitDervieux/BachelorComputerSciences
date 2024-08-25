package view.enums;

import view.interfaces.Describable;

/**
 * enum that hold external admin panel.
 */
public enum AdminPanel implements Describable {

  ADVANCE_DAY("Advance day"),
  VIEW_ALL_SIMPLE("List all members in a simple way"),
  VIEW_ALL_VERBOSE("List all members in a verbose way"),
  EXIT("Exit");


  private final String description;

  AdminPanel(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
