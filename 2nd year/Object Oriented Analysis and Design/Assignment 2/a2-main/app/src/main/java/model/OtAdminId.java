package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a model class for the admin ID.
 */
public class OtAdminId {

  private ArrayList<String> adminId = new ArrayList<String>();
  private final Random random = new Random();
  
  private String generateMemberId() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      int number = random.nextInt(36) + 97;
      if (number > 122) {
        number -= 75;
      }
      char s = (char) number;
      builder.append(s);
    }

    return builder.toString();
  }

  /**
 * Creates a member ID.
 *
 * @return  The generated member ID.
 */
  public String createMemberId() {
    String id = generateMemberId();
    while (adminId.contains(id)) {
      id = generateMemberId();
    }
    adminId.add(id);
    return id;
  }   
    
}
