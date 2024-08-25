package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;
import model.interfaces.ItemOperations;
import model.interfaces.UserOperations;

/**
 * This is the Item Class.
 */
public class Item  implements ItemOperations {

  /**
   * This is the category of the Items.
   */
  public enum Category {

    None("None"),
    Tool("Tool"),
    Vehicle("Vehicle"),
    Game("Game"),
    Toy("Toy"),
    Sport("Sport"),
    Other("Other");

    private String category;

    Category(String category) {
      this.category = category;
    }

    public String asString() {
      return category;
    }
  }

  /**
   * Class to hold item name.
   */
  public static class ItemName {
    private String itemName;

    /**
     * Item name constructor.
     *
     * @param itemName the name of the item.
     */
    public ItemName(String itemName) {
      if (itemName == null || itemName.length() < 2) {
        throw new IllegalArgumentException("The item name must be longer than 2 characters");
      }
      this.itemName = itemName;
    }

    public String asString() {
      return itemName;
    }
  }

  /**
   * Class to hold a short description.
   */
  public static class ItemShortDescription {
    private String shortDescription;

    /**
     * The constructor of the ItemShortDescription.
     *
     * @param shortDescription the short description.
     */
    public ItemShortDescription(String shortDescription) {
      if (shortDescription == null || shortDescription.length() < 2) {
        throw new IllegalArgumentException(
            "The item description must be longer than 2 characters");
      }
      this.shortDescription = shortDescription;
    }

    public String asString() {
      return shortDescription;
    }
  }

  /**
   * Class to hold the creation date.
   */
  public static class ItemDateOfCreation {
    private String dateOfCreation;

    /**
     * Constructor for ItemDateOfCreation.
     *
     * @param dateOfCreation the date.
     */
    public ItemDateOfCreation(String dateOfCreation) {
      if (dateOfCreation == null || dateOfCreation.length() != 10) {
        throw new IllegalArgumentException("The Date of creation is not appropriate");
      }
      this.dateOfCreation = dateOfCreation;
    }

    public String asString() {
      return dateOfCreation;
    }
  }

  /**
   * Class to hold the per day cost of an item.
   */
  public static class ItemCostPerDay {
    private float itemCostPerDay;

    /**
     * Constructor for the per day cost.
     *
     * @param itemCostPerDay per day cost.
     */
    public ItemCostPerDay(String itemCostPerDay) {
      try {
        this.itemCostPerDay = Float.parseFloat(itemCostPerDay);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("The amount entered is not a valid float.");
      }
    }

    public String asString() {
      return Float.toString(itemCostPerDay);
    }

    public float asFloat() {
      return itemCostPerDay;
    }
  }

  private ItemName name;
  private ItemShortDescription shortDescription;
  private ItemDateOfCreation dateOfCreation;
  private ItemCostPerDay costPerDay;
  private Category category;
  private User owner;
  private String itemId;
  private LinkedList<Contract> contracts = new LinkedList<>();

  /**
   * This is the Item class.
   *
   * @param name             is the name.
   * @param shortDescription is the short description.
   * @param dateOfCreation   is the date of creation of the Item.
   * @param costPerDay       is the cost per day.
   * @param category         is the category.
   * @param owner            is the owner of the item.
   */
  public Item(ItemName name, ItemShortDescription shortDescription,
      ItemDateOfCreation dateOfCreation,
      ItemCostPerDay costPerDay, Category category, UserOperations owner) {
    this.name = name;
    this.shortDescription = shortDescription;
    this.dateOfCreation = dateOfCreation;
    this.costPerDay = costPerDay;
    this.category = category;
    this.owner = (User) owner;
    LocalDateTime currentDateTime = LocalDateTime.now();
    try {
      // Help to not have an entry on the same milliseconds to avoid duplicate
      Thread.sleep(1);
    } catch (InterruptedException e) {
      // Handle the InterruptedException, if needed
      e.printStackTrace();
    }
    // Define a custom date and time format with milliseconds
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    // Format the current date and time as a String
    String formattedDateTime = currentDateTime.format(formatter).replaceAll(" ", "");
    itemId = formattedDateTime;
  }

  /**
   * This is the Item class for view based operations.
   *
   * @param name             is the name.
   * @param shortDescription is the short description.
   * @param dateOfCreation   is the date of creation of the Item.
   * @param costPerDay       is the cost per day.
   * @param category         is the category.
   */
  public Item(ItemName name, ItemShortDescription shortDescription,
      ItemDateOfCreation dateOfCreation,
      ItemCostPerDay costPerDay, Category category) {
    this.name = name;
    this.shortDescription = shortDescription;
    this.dateOfCreation = dateOfCreation;
    this.costPerDay = costPerDay;
    this.category = category;

  }

  public ItemName getName() {
    return name;
  }

  public ItemShortDescription getShortDescription() {
    return shortDescription;
  }

  public ItemDateOfCreation getDayOfCreation() {
    return dateOfCreation;
  }

  public ItemCostPerDay getCostPerDay() {
    return costPerDay;
  }

  public Category getCategory() {
    return category;
  }

  public String getOwnerId() {
    return owner.getMemberId();
  }

  public String getNameAsString() {
    return getName().asString();
  }

  public Iterable<Contract> getContracts() {
    return Collections.unmodifiableList(contracts);
  }

  public String getItemId() {
    return itemId;
  }


  /**
   * Checks whether an item is available for a given period.
   *
   * @param startDateStr start date
   * @param endDateStr   endDate
   * @param checkDateStr date to check
   * @return availability
   */
  public static boolean checkAvailability(String startDateStr,
      String endDateStr, String checkDateStr) {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    try {
      Date startDate = sdf.parse(startDateStr);
      Date endDate = sdf.parse(endDateStr);
      Date checkDate = sdf.parse(checkDateStr);

      // Check if checkDate is within the range
      if (checkDate.compareTo(startDate) >= 0 || checkDate.compareTo(endDate) <= 0) {
        return true;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Validate contracts start date.
   *
   * @param startDateStr start date
   * @return validity
   */
  public static boolean validateStartDate(String startDateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    // Set the desired time zone (e.g., UTC)
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    try {
      Date startDate = sdf.parse(startDateStr);

      // Get the current date in the same time zone
      Date currentDate = new Date();
      // Set the current date's time zone to match the SimpleDateFormat
      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      currentDate = sdf.parse(sdf.format(currentDate));

      // Check if startDate is today or in the future
      if (startDate.compareTo(currentDate) >= 0) {
        return true;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Create a contract for an item.
   *
   * @param startDate Start of the contract
   * @param endDate   End of the contract
   * @param borrower  the user who borrows.
   */
  public Contract createContract(String startDate, String endDate, User borrower) {

    for (Contract c : contracts) {

      if (!validateStartDate(startDate)) {
        throw new IllegalArgumentException("Invalid start date,"
            + " can not create a contract for a passed date");
      }

      if (checkAvailability(c.getStartDate(), c.getEndDate(), startDate)) {
        throw new IllegalArgumentException(
            "Start date overlaps with existing contract, should be before "
                + c.getStartDate());
      }

      if (checkAvailability(c.getStartDate(), c.getEndDate(), endDate)) {
        throw new IllegalArgumentException(
            "End date overlaps with existing contract , should be before "
                + c.getStartDate() + " after "
                + c.getEndDate());
      }

    }

    Contract newContract = new Contract(contracts.size(),
        startDate, endDate, this, this.owner, borrower);

    Float totalCost = this.costPerDay.asFloat() * newContract.getLength();

    if (borrower.getCredits() < totalCost) {
      throw new IllegalArgumentException("You do not have sufficient credits to borrow this item");
    }

    // reserve credits from borrower
    borrower.decrementCredits(totalCost);
    newContract.setReservedValue(totalCost);

    // add the contract.
    contracts.add(newContract);

    return newContract;
  }

  /**
   * check if an item has an active contract.
   *
   * @return true if the item has an active contract
   */
  public boolean hasActiveContract() {
    for (Contract c : contracts) {

      if (c.getIsActive()) {
        return true;
      }
    }

    return false;
  }

  /**
   * Add a contract to the list of contracts.

   * @param c is the contract.
   */
  public void addContract(Contract c) {
    contracts.add(c);
  }
}
