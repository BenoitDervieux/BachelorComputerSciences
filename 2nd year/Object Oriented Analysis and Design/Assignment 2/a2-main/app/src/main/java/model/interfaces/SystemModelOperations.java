package model.interfaces;

import model.Contract;
import model.Item;
import model.User;


/**
 * Interface to encapsulate System model operations.
 *
 */
public interface SystemModelOperations {

  User addUser(User user);

  User addUser(String name, String email, String phone, String password);

  void removeUser();

  Iterable<UserOperations> getAllUsers();

  User authenticateUser(String email, String passWord);

  void updateUserName(String newName);

  void updateUserPhoneNumber(String newNumber);

  void updateUserEmail(String newEmail);

  void updateUserPassword(String newPass);

  void purchaseCredits(float amount);

  Iterable<model.Item> getItemsUser();

  Iterable<Item> getItemsUser(UserOperations user);

  Iterable<Object> getItemsUserAndContract();

  Iterable<Object> getItemsUserAndContract(UserOperations user);

  Iterable<model.Item> getAllItems();

  Iterable<Item> getLendingItems();

  boolean deleteItemUser(String i);

  Item addItemUser(model.Item i, User user);

  Item getItemByName(String s);

  void replaceItem(Item previous, Item toReplace);

  boolean checkItemOwnership(String s);

  Contract addContractToUser(Item item, String startDate, String endDate);

  Contract addContractToUser(Item item, String startDate, String endDate, User user);

  User getUserByName(String name);

  void advanceDayCounter();

  Iterable<Contract> getAllContracts();

  Iterable<Object> getUserItemsContract();
}
