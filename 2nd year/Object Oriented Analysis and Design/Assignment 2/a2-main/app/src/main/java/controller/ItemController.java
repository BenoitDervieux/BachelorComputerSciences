package controller;

import model.Contract;
import model.Item;
import model.Item.ItemDateOfCreation;
import model.User;
import model.interfaces.UserOperations;

/**
 * This class is the item controller.
 */
public class ItemController {

  /**
   * Runs the item menu view and the user interface.
   *
   * @param uiItem is the Item Menu view.
   * @Param user is the user.
   * @param system is the system model interface.
   */
  public void run(view.ItemMenuView uiItem, model.interfaces.SystemModelOperations system,
      UserOperations user) {

    view.ItemMenuView.ItemMenuAction action = uiItem.showItemMenuAction();

    while (action != view.ItemMenuView.ItemMenuAction.EXIT) {
      switch (action) {
        case CREATE_item:
          addItem(uiItem, system, (User) user);
          break;
        case SHOW_item:
          showItems(uiItem, system);
          break;
        case DELETE_item:
          deleteItem(uiItem, system);
          break;
        case UPDATE_item:
          updateItemInformation(uiItem, system);
          break;
        case EXIT:
          break;
        default:
          throw new AssertionError(action.name());
      }
      action = uiItem.showItemMenuAction();
    }
  }

  private void addItem(view.ItemMenuView uiItem,
      model.interfaces.SystemModelOperations system, User user) {
    Item item = uiItem.getItemToAdd();

    Item addedItem = system.addItemUser(item, user);
    if (addedItem != null) {
      uiItem.printItemAdded(addedItem);
    } else {
      uiItem.itemAddFail();
    }
    
  }

  /**
   * Print all the items.
   *
   * @param uiItem is the Item Menu view.
   * @param user   is the user interface.
   */
  private void showItems(view.ItemMenuView uiItem, model.interfaces.SystemModelOperations system) {
    uiItem.printItemsAndContract(system.getItemsUserAndContract());
  }

  /**
   * Print if a user has been deleted or not.
   *
   * @param uiItem is the Item Menu view.
   * @param user   is the user interface.
   */
  private void deleteItem(view.ItemMenuView uiItem, model.interfaces.SystemModelOperations system) {
    String itemToDelete = uiItem.getItemToRemove();
    boolean deleted = system.deleteItemUser(itemToDelete);
    if (deleted) {
      uiItem.messageDeletionItem(itemToDelete);
    } else {
      uiItem.messageNotDeletionItem(itemToDelete);
    }
  }

  private void updateItemInformation(view.ItemMenuView uiItem,
        model.interfaces.SystemModelOperations system) {
    String itemToUpdate = uiItem.askForItemUpdate();
    Item item = system.getItemByName(itemToUpdate);
    if (item == null) {
      uiItem.messageItemNotExisting(itemToUpdate);
      return;
    }
    Iterable<Contract> contracts = item.getContracts();
    ItemDateOfCreation itemDate = item.getDayOfCreation();
    Item replacedItem = uiItem.updateItemInformation(itemDate, item);
    for (Contract c : contracts) {
      replacedItem.addContract(c);
    }
    system.replaceItem(item, replacedItem);
  }
}
