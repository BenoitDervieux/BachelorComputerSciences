package controller;

import model.Contract;
import model.Item;
import model.interfaces.SystemModelOperations;
import view.ContractData;
import view.Ui;

/**
 * This class handles the responsibilities related to the contacts.
 */
public class ContractController {

  /**
   * Opens a page to create a contract.
   */
  public void openCreateContractPage(SystemModelOperations sys, Ui ui) {

    Item item = ui.showAllItems(sys.getLendingItems());

    try {
      if (item != null) {
        ContractData dates = ui.promptContractDates();
        Contract c = sys.addContractToUser(item, dates.getStartDate(), dates.getEndDate());
        ui.printContract(c);
      }

    } catch (Exception e) {
      ui.printError(e);
    }

  }

}
