package view;

/**
 * class to communicate login data from view.
 */
public class LoginData {
  
  private String email;
  private String password;

  /**
   * Constructor for register user data .
   *
   * @param email       of the user
   * @param password    of the user
   */
  public LoginData(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
