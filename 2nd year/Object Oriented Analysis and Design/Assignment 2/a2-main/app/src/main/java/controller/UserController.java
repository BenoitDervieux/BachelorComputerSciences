package controller;

import model.interfaces.SystemModelOperations;
import model.interfaces.UserOperations;
import view.ItemMenuView;
import view.Ui;
import view.enums.UserPage;

/**
 * Handles user related operations at a top level.
 */
public class UserController {
  private UserOperations user;
  private Ui ui;

  /**
   * Constructor for the UserController.
   *
   * @param usr user to be logged in
   */
  public UserController(UserOperations usr, Ui ui) {
    user = usr;
    this.ui = ui;

  }

  /**
   * logs in a user.
   */
  public void login(SystemModelOperations sys) {

    

    ui.printLoginWelcomeMessage();
    ui.printCreditBalance(user);

    UserPage selection = ui.printUserMenu();

    while (selection != UserPage.EXIT) {

      switch (selection) {
        case PROFILE_PAGE:
          ProfileController pc = new ProfileController();
          pc.openProfileOptions(sys, ui, user, this);
          break;

        case ITEM_MENU:
          controller.ItemController itemController = new ItemController();
          ItemMenuView itemMenuView = new ItemMenuView();
          itemController.run(itemMenuView, sys, user);
          break;

        case BORROW:
          ContractController cc = new ContractController();
          cc.openCreateContractPage(sys, ui);
          break;

        case LIST_SYSTEM: // this needs to be updated
          ui.printObjects(sys.getUserItemsContract());
          break;

        case PURCHASE:
          sys.purchaseCredits(ui.getPurchaseSize());
          break;
          
        default:
          break;
      }

      // if user is deleted quit
      if (user == null) {
        return;
      }

      ui.printLoginWelcomeMessage();
      ui.printCreditBalance(user);
      selection = ui.printUserMenu();
    }
  }

  /**
   * logout by setting the current user to null.
   */
  public void logOut() {
    user = null;
    ui.printLogoutMessage();
  }
}
