package model.interfaces;

import model.Contract;
import model.Item;
import model.User;

/**
 * This interface is used for loading and saving users.
 */
public interface Persistence {

  void createUser(User user);
  
  void createItem(Item item);

  void createItem(Item item, User user);

  void createContract(Contract contract);

  void createContract(Contract contract, Item item);

  User getUserById(String memberId);

  Item getItemById(String itemId);

  Contract getContractById(int contractId);

  void updateUser(User user);

  void updateItem(Item item);

  void updateContract(Contract contract);

  void deleteUser(String userId);

  void deletItem(String itemId);

  void deleteContract(int contractId);

  User[] loadUsers();

  void saveUsers(User[] users);

  Item[] loadItems(User[] users);

  void saveItems(Item[] items);

  Contract[] loadContract(Item[] items);

  void saveContracts(Contract[] contracts);
    
}
