package model.interfaces;

import model.Contract;
import model.Item.Category;
import model.Item.ItemCostPerDay;
import model.Item.ItemDateOfCreation;
import model.Item.ItemName;
import model.Item.ItemShortDescription;

/**
 * Interface to encapsulate item operations.
 */
public interface ItemOperations {

  public ItemName getName();

  public ItemShortDescription getShortDescription();

  public ItemDateOfCreation getDayOfCreation();

  public ItemCostPerDay getCostPerDay();

  public Category getCategory();

  public String getOwnerId();

  public String getNameAsString();

  public Iterable<Contract> getContracts();

  public String getItemId();
  
} 
