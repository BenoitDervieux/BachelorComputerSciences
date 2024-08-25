package controller;

import model.Contract;
import model.Item;
import model.interfaces.SystemModelOperations;
import model.interfaces.UserOperations;
import view.Ui;
import view.enums.AdminPanel;

/**
 * Handles admin panel related operations.
 */
public class AdminPanelController {

  /**
   * Start the admin panel.
   *
   * @param sys the system model.
   * @param ui  the ui.
   */
  public void startAdminPage(SystemModelOperations sys, Ui ui) {

    AdminPanel selection = ui.printAdminPanel();

    while (selection != AdminPanel.EXIT) {

      switch (selection) {

        case ADVANCE_DAY:
          sys.advanceDayCounter();
          Iterable<Contract> contracts = sys.getAllContracts();
          ui.printAllContracts(contracts);
          break;

        case VIEW_ALL_SIMPLE:
          Iterable<UserOperations> usrs = sys.getAllUsers();
          for (UserOperations u : usrs) {
            ui.printUser(u);
          }

          break;

        case VIEW_ALL_VERBOSE:
          Iterable<UserOperations> users = sys.getAllUsers();

          for (UserOperations u : users) {
            ui.printUser(u);
            Iterable<Item> items = sys.getItemsUser(u);

            for (Item i : items) {
              ui.printItem(i);
              Iterable<Contract> cont = i.getContracts();

              for (Contract c : cont) {
                if (!c.getIsExpired()) {  //current contracts was interpreted as non expired  
                  ui.printContract(c);
                }
              }
            }

          }
          break;

        default:
          break;
      }

      selection = ui.printAdminPanel();
    }
  }
}
