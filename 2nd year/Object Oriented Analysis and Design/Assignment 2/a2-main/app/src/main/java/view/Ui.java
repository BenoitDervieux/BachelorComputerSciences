package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.interfaces.UserOperations;
import view.enums.AdminPanel;
import view.enums.MainMenu;
import view.enums.ProfileOptions;
import view.enums.ProfilePage;
import view.enums.UserPage;
import view.interfaces.Describable;

/**
 * This is the class that handles the view operations and input collection.
 */
public class Ui {

  Scanner sc = new Scanner(System.in, "UTF-8");

  /**
   * Display the starting menu and main menu options.
   *
   * @return the selected option.
   */
  public MainMenu displayStartingMenu() {
    System.out.println("\nWelcome to the lending stuff system\n");
    return displayEnumOptions(MainMenu.class);
  }

  /**
   * Collect data to create a user.
   *
   * @return String array with name, email and phone number in the given order
   */
  public UserData getUserData() {
    String[] prompts = { "Enter the name of the user: ", "Enter the email of the user: ",
        "Enter the phone number of the user: ", "Enter a password: " };
    String[] inputs = new String[prompts.length];

    System.out.println("\nWelcome to the registration page !!!\n");
    waitForEnterToContinue();

    for (int i = 0; i < prompts.length; i++) {
      System.out.println(prompts[i]);
      inputs[i] = sc.nextLine();

      if (inputs[i].trim().equals("")) {
        System.out.println("Invalid input please retry!!\n");
        i--;
      }
    }

    return new UserData(inputs[0], inputs[1], inputs[2], inputs[3]);
  }

  /**
   * Print the user in a simple way.
   *
   * @param user the user.
   */
  public void printUser(UserOperations user) {
    StringBuilder str = new StringBuilder();
    str.append("\nThe name of this user is " + user.getName() + ". Its email is "
        + user.getEmail() + ". \nHis/her credit amount is " + user.getCredits()
        + "\nHis/Hers item count is: "
        + user.getItemCount() + "\n" + "His/Her ID is: " + user.getMemberId() + "\n");

    System.out.println("\n!!!---User---!!!\n");
    System.out.println(str.toString());
  }

  /**
   * Prints an item.
   *
   * @param item item
   */
  public void printItem(Item item) {
    StringBuilder str = new StringBuilder();

    str.append("Item name: " + item.getNameAsString() + "\n"
        + "Item description: " + item.getShortDescription().asString() + "\n"
        + "Item Category: " + item.getCategory().asString() + "\n"
        + "Per day cost: " + item.getCostPerDay().asString() + "\n"
        + "Registration Date: " + item.getDayOfCreation().asString() + "\n");

    System.out.println("\n!!!---Item---!!!\n");
    System.out.println(str.toString());
  }


  /**
   * shows whether the registration is successful.
   *
   * @param usr registered user object that gets returned.
   */
  public void viewRegStatus(UserOperations usr) {
    if (usr == null) {
      System.out.println("\nNew user registration failed !!!\n");
    } else {
      System.out.println("\nUser \"" + usr.getName() + "\" registered successfully\n");
    }
  }

  public AdminPanel printAdminPanel() {
    return displayEnumOptions(AdminPanel.class);
  }

  public UserPage printUserMenu() {
    return displayEnumOptions(UserPage.class);
  }

  public ProfileOptions printProfilePageOptions() {
    System.out.println("Welcome to the profile page !!\n");
    return displayEnumOptions(ProfileOptions.class);
  }

  /**
   * Gets user confirmation for a decision.
   *
   * @return decision
   */
  public boolean getConfirmation() {
    String[] options = { "Yes", "No" };
    System.out.println("\nAre you sure you want to continue ?");
    return printOptions(options) == 1;
  }

  /**
   * Displays user details.
   *
   * @param user to be displayed
   */
  public void viewUserProfile(UserOperations user) {
    System.out.println("Name: " + user.getName());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Phone Number: " + user.getPhoneNumber());
    System.out.println("Date joined: " + user.getDateReg());
    System.out.println("Credits: " + user.getCredits());
    System.out.println("Member id: " + user.getMemberId());
  }

  /**
   * Displays the menu for updating profile data.
   *
   * @return selected update
   */
  public ProfilePage viewUpdateProfileMenu() {
    return displayEnumOptions(ProfilePage.class);
  }

  public String getNewName() {
    String[] prompts = { "Enter new name: " };
    return getInputPrompts(prompts)[0]; // since its just one prompt directly sends the input
  }

  public String getNewEmail() {
    String[] prompts = { "Enter new email: " };
    return getInputPrompts(prompts)[0]; // since its just one prompt directly sends the input
  }

  public String getNewPhoneNumber() {
    String[] prompts = { "Enter new phone number: " };
    return getInputPrompts(prompts)[0]; // since its just one prompt directly sends the input
  }

  public String getNewPassword() {
    String[] prompts = { "Enter new password: " };
    return getInputPrompts(prompts)[0]; // since its just one prompt directly sends the input
  }

  /**
   * Print a list of contracts.
   *
   * @param contracts the contracts iterable.
   */
  public void printAllContracts(Iterable<Contract> contracts) {
    for (Contract c : contracts) {
      System.out.println("\n");
      printContract(c);
      System.out.println("\n");
    }
  }

  public void printError(Exception e) {
    System.out.println(e.getMessage());
  }

  /**
   * Get login data from user.
   *
   * @return login data.
   */
  public LoginData getLoginData() {
    String[] prompts = { "Enter email: ", "Enter password: " };
    String[] inputs = getInputPrompts(prompts);

    return new LoginData(inputs[0], inputs[1]);
  }

  public void printLoginWelcomeMessage() {
    System.out.println("\nWelcome to your profile !!!\n");
  }

  public void printInvalidCredentialsMessage() {
    System.out.println("\nInvalid login credentials, please try again !!\n");
  }

  public void printLogoutMessage() {
    System.out.println("\nYou are being logged out !!!\n");
  }

  public void printCreditBalance(UserOperations u) {
    System.out.println("\nYour credit balance is: " + u.getCredits() + "\n");
  }

  /**
   * Get credit purchase amount.
   *
   * @return amount to be purchased
   */
  public float getPurchaseSize() {
    System.out.print("Enter desired credit amount: ");

    try {
      float purchaseSize = sc.nextFloat();
      return purchaseSize;
    } catch (java.util.InputMismatchException e) {
      System.out.println("Invalid input. Please enter a valid numeric value.");
      return 0.0f;
    }
  }

  /**
   * Getting a list of items and selecting one.
   *
   * @param items the list of items.
   * 
   * @return selected item
   */
  public Item showAllItems(Iterable<Item> items) { // try to improve

    List<Item> itemList = new ArrayList<>();

    // Populate the itemList with items from the Iterable
    for (Item item : items) {
      itemList.add(item);
    }

    if (itemList.size() < 1) {
      System.out.println("No items in inventory");
      return null;
    }

    String[] options = new String[itemList.size()];
    int count = 0;
    for (Item i : itemList) {
      options[count] = i.getName().asString(); // Assuming getName() returns a String
      count++;
    }

    int itemIndex = printOptions(options) - 1; // Adjust for 0-based indexing

    return itemList.get(itemIndex);
  }

  /**
   * Get start date and end date of a contract in order.
   */
  public ContractData promptContractDates() {

    String[] dates = new String[2];
    boolean validInput = false;
    String[] inputPrompts = { "Enter a start date (yyyy/MM/dd): ",
        "Enter an end date (yyyy/MM/dd): " };

    while (!validInput) {
      dates = getInputPrompts(inputPrompts);

      SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");

      try {
        Date starDate = sdfInput.parse(dates[0]);
        String formattedStartDate = sdfInput.format(starDate);
        dates[0] = formattedStartDate;
        Date endDate = sdfInput.parse(dates[1]);
        String formattedEndDate = sdfInput.format(endDate);
        dates[1] = formattedEndDate;

        // end loop
        validInput = true;
      } catch (ParseException e) {
        System.out.println("Invalid date format. Please use yyyy/MM/dd.");
      }

    }

    return new ContractData(dates[0], dates[1]);
  }

  public void printContractCreationMessage() {
    System.out.println("Contract was successfully created !!!");
  }

  /**
   * Prints a contract.
   *
   * @param c the contract.
   */
  public void printContract(Contract c) {
    StringBuilder str = new StringBuilder();

    str.append("\nContract made between " + c.getLender().getName()
        + " and " + c.getBorrower().getName() + " for a "
        + c.getItem().getNameAsString() + " from " + c.getStartDate()
        + " till " + c.getEndDate() + " for a period of " + c.getLength()
        + " days \n" + "Current status of the contact is :- expired: " + c.getIsExpired()
        + " Active : " + c.getIsActive() + "\n");

    System.out.println(str.toString());
  }

  /**
   * Wait for the user to press any key to continue.
   */
  public void waitForEnterToContinue() {
    System.out.println("\nPress Enter to continue...\n");
    sc.nextLine(); // Wait for user input (Enter key)
    sc.nextLine();
  }

  /**
   * This is a helper method to get the input for a set of menu options.
   *
   * @param options string array of options
   * @return the selected option
   */
  private int printOptions(String[] options) {
    boolean validInput = false;

    while (!validInput) {
      // Print the options
      for (int i = 0; i < options.length; i++) {
        System.out.println((i + 1) + ". " + options[i]);
      }

      System.out.print("\nSelect an action: \n");

      try {
        int in = sc.nextInt();

        if (in != 0 && in <= (options.length)) {
          validInput = true;
          return in;
        } else {
          // if the selection is not in range
          System.out.println("\nInvalid selection please retry!\n");
        }

      } catch (Exception e) {
        System.out.println("\nInvalid selection please retry!\n");
        sc.next();
      }

    }

    return -1;
  }

  /**
   * used to get inputs for a set of prompts.
   *
   * @param prompts the list of prompts
   * @return a string array of inputs in the order of the prompts
   */
  private String[] getInputPrompts(String[] prompts) {
    String[] inputs = new String[prompts.length];
    for (int i = 0; i < prompts.length; i++) {
      System.out.print(prompts[i]);
      inputs[i] = sc.next();

      if (inputs[i].trim().equals("")) {
        System.out.println("Invalid input please retry!!\n");
        i--;
      }
    }

    return inputs;
  }

  private <T extends Enum<T> & Describable> T displayEnumOptions(Class<T> enumClass) {
    T[] options = enumClass.getEnumConstants();
    String[] optionsStr = new String[options.length];

    if (options.length == 0) {
      throw new IllegalArgumentException("Enum class must have constants");
    }

    for (int i = 0; i < options.length; i++) {
      optionsStr[i] = options[i].getDescription();
    }

    int choice = printOptions(optionsStr);

    return options[choice - 1];
  }

  /**
   * Print all the types of Object it has been passed between user, items and
   * contract.
   *
   * @param iterable is all the types of object the project has.
   */
  public void printObjects(Iterable<Object> iterable) {
    for (Object o : iterable) {
      if (o instanceof UserOperations) {
        printUser((UserOperations) o);
      } else if (o instanceof Item) {
        System.out.print("  ");
        printItem((Item) o);
      } else if (o instanceof Contract) {
        System.out.print("    ");
        printContract((Contract) o);
      }
    }
  }

}
