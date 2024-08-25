package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.interfaces.UserOperations;

/**
 * This is a model class for the user.
 */
public class User implements UserOperations {

  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private String memberId;
  private String dateReg;
  private int itemCount;
  private float credit;
  private OtAdminId idGiver = new OtAdminId();

  /**
   * Constructor for the user.
   *
   * @param name        of the user
   * @param email       of the user
   * @param phoneNumber of the user
   * @param password    of the user
   */
  public User(String name, String email, String phoneNumber, String password) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.memberId = idGiver.createMemberId();
    this.dateReg = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    credit = 0;
    itemCount = 0;
  }
  


  /**
   * Constructor for a user with credits.
   *
   * @param name           is the name.
   * @param email          is the email.
   * @param phoneNumber    is the phone number.
   * @param password       is the password.
   * @param credit         is the credit.
   */
  public User(String name, String email, String phoneNumber, String password,  float credit) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.memberId = idGiver.createMemberId();
    this.dateReg = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
    this.credit = credit;
    itemCount = 0;
  }


  /**
   * Contractor with a user.
   * Use to create a copy of the object.
   *
   * @param u user
   */
  public User(User u) {
    this.name = u.name;
    this.email = u.email;
    this.phoneNumber = u.phoneNumber;
    this.password = u.password;
    this.memberId = u.getMemberId();
    this.dateReg = u.getDateReg();
    credit = u.getCredits();
    itemCount = u.getItemCount();
  }

  public String getName() {
    return name;
  }

  public String getNameLowered() {
    return name.toLowerCase();
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getMemberId() {
    return memberId;
  }

  public String getDateReg() {
    return dateReg;
  }

  public String getPassword() {
    return password;
  }

  public float getCredits() {
    return credit;
  }

  public int getItemCount() { 
    return itemCount;
  }

  /*
   * Even though the setters are used it is only exposed to the system model and
   * the
   * user behavior that is required by others are exposed through an interface
   * UserOperations
   */

  protected void setName(String name) {
    this.name = name;
  }

  protected void setEmail(String email) {
    this.email = email;
  }

  protected void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  protected void setPassword(String password) {
    this.password = password;
  }

  public void incrementCredits(float amount) {
    credit += amount;
  }

  protected void decrementCredits(float amount) {
    credit -= amount;
  }

  protected void incrementItemCount() {
    itemCount++;
  }

  protected void decrementItemCount() {
    itemCount--;
  }

}
