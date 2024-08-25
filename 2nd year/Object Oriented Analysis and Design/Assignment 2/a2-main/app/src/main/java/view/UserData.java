package view;

/**
 * Class to communicate user data from.
 */
public class UserData extends LoginData {

  private String name;
  private String phoneNumber;

  /**
   * Constructor for register user data .
   *
   * @param name        of the user
   * @param email       of the user
   * @param phoneNumber of the user
   * @param password    of the user
   */
  public UserData(String name, String email, String phoneNumber, String password) {
    super(email, password);
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

}
