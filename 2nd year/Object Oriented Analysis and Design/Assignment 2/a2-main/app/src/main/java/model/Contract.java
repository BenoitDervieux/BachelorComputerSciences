package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import model.interfaces.ItemOperations;
import model.interfaces.TimeObserver;
import model.interfaces.UserOperations;


/**
 * This holds contract related data.
 */
public class Contract implements TimeObserver {
  private int contractId;
  private String startDate;
  private String endDate;
  private int length;
  private ItemOperations item;
  private User lender;
  private User borrower;
  private float reservedValue;
  private boolean isActive;
  private boolean isExpired;

  /**
   * Constructor for a contract.
   *
   * @param contractId id of the contract
   * @param startDate  start date of the contract.
   * @param endDate    end date of the contract.
   * @param item       specific item to be lent
   * @param lender     the lender.
   * @param borrower   the owner.
   */
  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "EI_EXPOSE_REP2", 
      justification = "I want the user to do the transaction")
  public Contract(int contractId, String startDate,
      String endDate, Item item, User lender, User borrower) {
    this.contractId = contractId;
    this.startDate = startDate;
    this.item = item;
    this.lender = lender;
    this.borrower = borrower;
    this.endDate = endDate;
    this.isActive = checkAndSetActiveIfCreatedToday();
    this.isExpired = false;
    this.length = calculateLength(startDate, endDate);
    reservedValue = 0;
  }

  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "EI_EXPOSE_REP2", 
      justification = "Sending the encapsulated version so cannot mutate the item")
  public ItemOperations getItem() {
    return item;
  }

  public int getContractId() {
    return contractId;
  }

  public String getStartDate() {
    return startDate;
  }

  public int getLength() {
    return length;
  }

  public String getEndDate() {
    return endDate;
  }

  public String getLenderId() {
    return lender.getMemberId();
  }

  public String getBorrowerId() {
    return borrower.getMemberId();
  }

  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "EI_EXPOSE_REP2", 
      justification = "Sending the encapsulated version so cannot mutate the user")
  public UserOperations getLender() {
    return lender;
  }

  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "EI_EXPOSE_REP2", 
      justification = "Sending the encapsulated version so cannot mutate the user")
  public UserOperations getBorrower() {
    return borrower;
  }

  public boolean getIsExpired() {
    return isExpired;
  }

  public boolean getIsActive() {
    return isActive;
  }

  protected void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  protected void setReservedValue(float val) {
    reservedValue = val;
  }

  private void expireContract() {
    isExpired = true;
    isActive = false;
    completeTransaction();
  }

  private void completeTransaction() {
    lender.incrementCredits(reservedValue);
  }

  public void refund() {
    borrower.incrementCredits(reservedValue);
  }


  private boolean checkAndSetActiveIfCreatedToday() {
    // Get the current date as a string in the "yyyy/MM/dd" format
    String currentDate = new SimpleDateFormat("yyyy/MM/dd")
        .format(Calendar.getInstance().getTime());

    // Check if the contract's start date matches the current date
    if (startDate.equals(currentDate)) {
      return true;
    }

    return false;
  }

  @Override
  public void update(int currentDayCount) {
    // Get the current date as a Date object
    Date currentDateObj = new Date();

    // Calculate the new date by adding currentDayCount days
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDateObj);
    calendar.add(Calendar.DAY_OF_YEAR, currentDayCount);
    Date newDate = calendar.getTime();

    // Format the new date as a string in the "yyyy/MM/dd" format
    String newDateString = new SimpleDateFormat("yyyy/MM/dd").format(newDate);

    // Check if the contract's start date matches the new date
    if (startDate.equals(newDateString)) {
      setActive(true);
    } else if (!isExpired) {
      try {
        // Parse the contract's end date as a Date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date endDateObj = sdf.parse(endDate);

        // Calculate the difference in days between the new date and contract end date
        long diffMillis = endDateObj.getTime() - newDate.getTime();
        long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        // Check if the contract has expired
        if (diffDays < 0) {
          expireContract();
        }
      } catch (ParseException e) {
        e.printStackTrace(); // Handle the exception appropriately, logging or error handling
      }
    }
  }

  private static int calculateLength(String startDateStr, String endDateStr) {

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      Date startDate = sdf.parse(startDateStr);
      Date endDate = sdf.parse(endDateStr);

      // Calculate the difference in milliseconds between endDate and startDate
      long diffMillis = endDate.getTime() - startDate.getTime();

      // Convert the difference to days
      int length = (int) (diffMillis / (24 * 60 * 60 * 1000)) + 1;
      // +1 is to include the current day

      return length;
    } catch (ParseException e) {
      e.printStackTrace();

    }
    return -1; // Return -1 in case of an error
  }

}
