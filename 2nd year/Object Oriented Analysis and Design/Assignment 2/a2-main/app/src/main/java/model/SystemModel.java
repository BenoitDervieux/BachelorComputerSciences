package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.interfaces.SystemModelOperations;
import model.interfaces.UserOperations;

/**
 * Holds the users and responsibility related to
 * manipulating user data.
 */
public class SystemModel implements SystemModelOperations {

  private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Item> items = new ArrayList<Item>();
  private Time time = new Time();
  private User loggedInUser;

  /**
   * Add a new user.
   *
   * @param name  of the user.
   * @param email of the user.
   * @param phone of the user.
   * @return added user.
   */
  public User addUser(String name, String email, String phone, String password) {
    // If the user is not validated return
    validateEmail(email);
    validatePhoneNumber(phone);
    User newUser = new User(name, email, phone, password);
    users.add(newUser);
    return newUser;
  }

  /**
   * Adds a user object to users.
   *
   * @param user new user to add.
   * @return added user.
   */
  public User addUser(User user) {
    // If the user is not validated return
    if (validateEmail(user.getEmail()) && validatePhoneNumber(user.getPhoneNumber())) {
      users.add(user);
    }
    return user;
  }

  /**
   * Removes a user from the list based on email.
   *
   */
  public void removeUser() {
    // Removing the users items 
    Iterator<Item> iterator = items.iterator();

    // Remove the user's items using the iterator
    while (iterator.hasNext()) {
      Item item = iterator.next();

      if (item.getOwnerId().equals(loggedInUser.getMemberId())) {
        Iterable<Contract> contracts = item.getContracts();
        // refund all future contracts
        for (Contract c : contracts) {
          c.refund();
        }

        iterator.remove(); // Use the iterator's remove method
      }
    }

    users.remove(loggedInUser);
  }

  /**
   * Updates a users name.
   *
   * @param newName new name of the user.
   */
  public void updateUserName(String newName) {
    // There is no check that needs to
    loggedInUser.setName(newName);
  }

  /**
   * Updates an existing users phone number.
   *
   * @param newNumber the new number of the member
   */
  public void updateUserPhoneNumber(String newNumber) {
    validatePhoneNumber(newNumber);
    loggedInUser.setPhoneNumber(newNumber);
  }

  /**
   * Updates an existing users email.
   *
   * @param newEmail the new email of the member
   */
  public void updateUserEmail(String newEmail) {
    validateEmail(newEmail);
    loggedInUser.setEmail(newEmail);
  }

  /**
   * Updates the users password.
   *
   * @param newPass the new password
   */
  public void updateUserPassword(String newPass) {
    loggedInUser.setPassword(newPass);
  }

  /**
   * Get a list of registered users in the system.
   *
   * @return iterable list of users.
   */
  public Iterable<UserOperations> getAllUsers() {
    Collections.sort(users, Comparator.comparing(UserOperations::getNameLowered));
    return Collections.unmodifiableList(users);
  }

  /**
   * Authenticates a user.
   *
   * @param email    email of the user
   * @param passWord password of the user
   * @return user to be logged in
   */
  public User authenticateUser(String email, String passWord) {
    for (User s : users) {
      if (s.getEmail().equals(email) && s.getPassword().equals(passWord)) {
        loggedInUser = s;
        return s;
      }
    }
    return null;
  }

  /**
   * Check whether the email is not available in the system.
   *
   * @param email email to be checked
   * @return whether it's unique
   */
  private boolean validateEmail(String email) {
    for (User s : users) {
      if (s.getEmail().equalsIgnoreCase(email)) {
        throw new IllegalArgumentException("Email already exists, Please retry!");
      }
    }

    return true;
  }

  /**
   * Check whether the phone number is not available in the system.
   *
   * @param phone number to ber checked
   * @return whether it's unique
   */
  private boolean validatePhoneNumber(String phone) {

    for (User s : users) {
      if (s.getPhoneNumber().equalsIgnoreCase(phone)) {
        throw new IllegalArgumentException("Phone number already exists, Please retry!");
      }
    }
    return true;
  }

  /**
   * Buying credits for an logged in user.
   *
   * @param amount amount to be purchased
   */
  public void purchaseCredits(float amount) {
    loggedInUser.incrementCredits(amount);
  }

  /**
   * Adds a item to a users item list.
   *
   * @param i the item.
   * @return the added item.
   */
  public Item addItemUser(Item i, User user) {

    if (user != null) {
      Item item = new Item(i.getName(), i.getShortDescription(),
          i.getDayOfCreation(), i.getCostPerDay(), i.getCategory(), user);
      items.add(item);
      user.incrementItemCount();
      user.incrementCredits(100);
      return item;
    } else {
      return null;
    }

  }

  /**
   * Deletes and item from a users inventory.
   *
   * @param i the item
   */
  public boolean deleteItemUser(String i) {
    for (Item a : items) {
      if (a.hasActiveContract()) {
        return false; // if the item has an active contract
      }
      if (a.getName().asString().equals(i) && a.getOwnerId().equals(loggedInUser.getMemberId())) {
        Iterable<Contract> contracts = a.getContracts();

        // refund incomplete contracts
        for (Contract c : contracts) {
          if (!c.getIsExpired()) {
            c.refund();
          }
        }

        items.remove(a);
        loggedInUser.decrementItemCount(); // right now only a logged in user can remove an item
        return true;
      }
    }
    return false;
  }

  /**
   * Get the users list of items.
   *
   * @return list of items.
   */
  public Iterable<Item> getAllItems() {
    ArrayList<Item> fix = new ArrayList<Item>();
    for (Item i : items) {
      fix.add(i);
    }
    Collections.sort(fix, Comparator.comparing(Item::getNameAsString));
    return Collections.unmodifiableList(fix);
  }

  /**
   * Get the logged in users list of items.
   *
   * @return list of items.
   */
  public Iterable<Item> getLendingItems() {
    ArrayList<Item> lendItems = new ArrayList<>();

    for (Item i : items) {

      if (!i.getOwnerId().equals(loggedInUser.getMemberId())) {
        lendItems.add(i);
      }
    }
    return Collections.unmodifiableList(lendItems);
  }

  /**
   * Get the users list of items.
   *
   * @return list of items.
   */
  public Iterable<Item> getItemsUser(UserOperations user) {
    ArrayList<Item> usersItems = new ArrayList<>();

    for (Item a : items) {
      if (a.getOwnerId().equals(user.getMemberId())) {
        usersItems.add(a);
      }
    }

    Collections.sort(usersItems, Comparator.comparing(Item::getNameAsString));

    return Collections.unmodifiableList(usersItems);
  }

  /**
   * Get the users list of items.
   *
   * @return list of items of the logged on user.
   */
  public Iterable<Item> getItemsUser() {

    return getItemsUser(loggedInUser);
  }

  /**
   * Get the users list of items and their contract.
   *
   * @return list of items.
   */
  public Iterable<Object> getItemsUserAndContract(UserOperations user) {
    ArrayList<Item> usersItems = new ArrayList<>();
    ArrayList<Object> usersItemsAndContract = new ArrayList<>();

    for (Item a : items) {
      if (a.getOwnerId().equals(user.getMemberId())) {
        usersItems.add(a);
      }
    }

    Collections.sort(usersItems, Comparator.comparing(Item::getNameAsString));

    for (Item i : usersItems) {
      usersItemsAndContract.add(i);
      for (Contract c : i.getContracts()) {
        usersItemsAndContract.add(c);
      }
    }

    return Collections.unmodifiableList(usersItemsAndContract);
  }

  /**
   * Get the users list of items.
   *
   * @return list of items of the logged on user.
   */
  public Iterable<Object> getItemsUserAndContract() {

    return getItemsUserAndContract(loggedInUser);
  }

  /**
   * Return an item by using the name.
   *
   * @param s it the string name of the item.
   * @return an item.
   */
  public Item getItemByName(String s) {
    for (Item i : items) {
      if (i.getName().asString().equals(s) /* && i.getOwner() == loggedInUser */) {
        return i;
      }
    }
    return null;
  }

  /**
   * This function replaces an item in the list.
   *
   * @param previous is the item to replace.
   */
  public void replaceItem(Item previous, Item toReplace) {
    int index = 0;
    for (Item i : items) {
      if (i == previous && i.getOwnerId().equals(loggedInUser.getMemberId())) {
        index = items.indexOf(previous);
      }
    }
    items.remove(index);

    items.add(new Item(toReplace.getName(), toReplace.getShortDescription(),
        toReplace.getDayOfCreation(), toReplace.getCostPerDay(),
        toReplace.getCategory(), loggedInUser));
  }

  /**
   * This function checks the ownership of an item.
   *
   * @param s is the item to replace.
   */
  public boolean checkItemOwnership(String s) {
    for (Item i : items) {
      if (i.getName().asString().equals(s) && i.getOwnerId().equals(loggedInUser.getMemberId())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adds a contract to the logged in user.
   *
   * @param item      the item in which the contract is for
   * @param startDate Start of the contract.
   * @param endDate   End of the contract
   * @return the contract.
   */
  public Contract addContractToUser(Item item, String startDate,
      String endDate) throws IllegalArgumentException {

    Contract c = item.createContract(startDate, endDate, loggedInUser);
    time.addObserver(c);
    return c;
  }

  /**
   * Adds a contract to any user.
   *
   * @param item      the item in which the contract is for
   * @param startDate Start of the contract.
   * @param endDate   End of the contract
   * @return the contract.
   */
  public Contract addContractToUser(Item item, String startDate,
      String endDate, User user) throws IllegalArgumentException {

    Contract c = item.createContract(startDate, endDate, user);
    time.addObserver(c);
    return c;
  }

  /**
   * Get all contracts.
   *
   * @return all the contracts.
   */
  public Iterable<Contract> getAllContracts() {
    Iterable<Item> items = getAllItems();
    LinkedList<Contract> allContacts = new LinkedList<>();

    for (Item i : items) {
      Iterable<Contract> contracts = i.getContracts();

      for (Contract c : contracts) {
        allContacts.add(c);
      }
    }

    return allContacts;
  }

  /**
   * Test method to try dummy entries.
   *
   * @param name is the name we search.
   * @return is the user we want.
   */
  public User getUserByName(String name) {
    for (User u : users) {
      if (u.getName().equals(name)) {
        return u;
      }
    }
    return null;
  }

  public void advanceDayCounter() {
    time.advanceDay();
  }

  /**
   * Returns the list of users, their items and their contract.
   */
  public Iterable<Object> getUserItemsContract() {
    Iterable<UserOperations> copyUsers = this.getAllUsers();
    List<Object> copyOfObject = new ArrayList<>();
    for (UserOperations uo : copyUsers) {
      copyOfObject.add(uo);
      for (Item i : this.getItemsUser(uo)) {
        copyOfObject.add(i);
        for (Contract c : i.getContracts()) {
          copyOfObject.add(c);
        }
      }
    }
    return copyOfObject;
  }

  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "EI_EXPOSE_REP2", 
      justification = "Sending the encapsulated version so cannot mutate the item")
  public UserOperations getLoggedInUser() {
    UserOperations copy = new User(loggedInUser);
    return copy;
  }

}
