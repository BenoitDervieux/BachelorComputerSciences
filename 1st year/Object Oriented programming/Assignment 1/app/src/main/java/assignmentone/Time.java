package assignmentone;

/**
 * This class handles functions about time conversion.
 */
public class Time {
  /**
   * Transforms a time in hour, minutes and seconds in seconds only.

   * @param h corresponds to the hours.
   * @param m corresponds to the minutes.
   * @param s corresponds to the seconds.
   * @return a time in seconds.
   */
  public int toSeconds(int h, int m, int s) {
    int total = h * 3600 + m * 60 + s;
    return total;
  }

  /**
   * This method takes a line of numbers as String and send the 3 first back as an array of integers.

   * @param a is the line sent as a String that contains the integers.
   * @return an array of int.
   */
  public int[] threeInt(String a) {
    int[] b = new int[3];
    String[] threeNumbers = a.split(" ");
    int iter = 0;
    for (String e : threeNumbers) {
      if (e.equals("")) {
        // If it is a white space we do not count it
        // and it does not appear as a 0
        continue;
      } else {
        try {
          // Convert any string number into int number
          if (e.equals("")) {
            iter++;
            continue;
          } else {
            b[iter] = Integer.parseInt(e);
            iter++;
          }
        } catch (Exception p) {
          continue;
        }
      } 
    }
    return b; 
  }
}
