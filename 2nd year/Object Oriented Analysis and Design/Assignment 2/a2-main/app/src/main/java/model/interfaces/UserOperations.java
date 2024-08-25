package model.interfaces;


/**
 * Encapsulates behavior exposed for a user class.
 */
public interface UserOperations {

  String getName();

  String getNameLowered();

  String getEmail();

  String getPhoneNumber();

  String getMemberId();
  
  String getDateReg();

  float getCredits();

  int getItemCount();

  String getPassword();
}
