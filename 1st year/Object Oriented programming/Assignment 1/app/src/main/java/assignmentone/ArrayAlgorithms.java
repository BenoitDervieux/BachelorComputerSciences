package assignmentone;

import java.util.Random;

/**
 * This class handles a series of methods on integers arrays.
 */
public class ArrayAlgorithms {
  // Instance of a random number
  private static final Random indexArrayRandom = new Random();
  /**
   * Iterates through an array of integers and return the average value.

   * @param a is an array of integerss.
   * @return a double corresponding of the average value inside the array.
   */
  public double average(int[] a) {
    double sum = 0.0;
    double iteration = 0.0;
    double result = 0.0;
    for (int p : a) {
      sum += p;
      iteration += 1;
    }
    result = sum / iteration;
    return result;
  }

  /**
   * Returns the maximum value inside an array of integers.

   * @param a is an array of integers.
   * @return an integer which is the maximum value of the array.
   */
  public int maxValue(int[] a) {
    int maximum = a[0];
    for (int i = 0; i < a.length; i++) {
      if (a[i] > maximum) {
        maximum = a[i];
      } 
    }
    return maximum;
  }

  /**
   * Return the index at which the array has its minimum value.

   * @param a is an array of integers.
   * @return the index of the integers at which it has its minimum value.
   */
  public int minIndex(int[] a) {
    int index = 0;
    int minimum = a[0];
    int iteration = 0;
    for (int p : a) {
      if (p < minimum) {
        minimum = p;
        index = iteration;
      } 
      iteration++;
    }
    return index;
  }

  /**
   * Shifts the elements of an arrays of integers one case to the left.

   * @param a is an array of integers.
   * @return an array of integers.
   */
  public int[] shift(int[] a) {
    int buffer = a[0];
    int[] bufferArray = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      if (i == (a.length - 1)) {
        bufferArray[i] = buffer;
      } else {
        bufferArray[i] = a[i + 1];
      }
    }
    return bufferArray;
  }

  /**
   * Shuffles the values of an array of integers in a random way.

   * @param a is an array of integers.
   * @return an array of integers.
   */
  public int[] shuffle(int[] a) {
    // Initialize a new array of same size
    int[] b = new int[a.length];
    // variable to store random number
    int numberRandom;
    // length of the array for loop
    int lengthArray = b.length;
    // for loop up to the length of the array, 5 in the test
    for (int i = 0; i < lengthArray; i++) {
      // initialize a random number from 0 to the length of a
      numberRandom = indexArrayRandom.nextInt(a.length);
      // in order 0 to 4, copy the number got by of a[random]
      b[i] = a[numberRandom];
      // remove the number from the array at position
      a = removeElement(a, numberRandom);
    }
    // return the array b
    return b;
  }

  /**
   * Remove an element from an array when its index is known.

   * @param a is an array of integers.
   * @param b is the index at which we want to remove the array element.
   * @return an array of integer shorter of one element.
   */
  private int[] removeElement(int[] a, int b) {
    // allow to check if the indentation has passed or not
    Boolean indexation = false;
    // Create an array of a size a minus one
    int[] c = new int[a.length - 1];
    // for loop to copy all the elements except the one we just added
    for (int i = 0; i < a.length - 1; i++) {
      if (i == b) {
        // Trigger true to jump over the int we do not want
        indexation = true;
      }
      if (indexation == false) {
        // copy the normal array before the index
        c[i] = a[i];
      } else {
        // copy the array jumping over the index
        c[i] = a[i + 1];
      }
    }
    return c;
  }
  /**
  * Calculate the real number of numbers with no white space to get the real length of an array.
  * This method is different than array.length as supplementary white space in the input 
  * can be considered as elements of the array when using split.

  * @param a is an array of number in a String form.
  * @return a number which is the size of an array of String
  */
  public int realSizeArray(String[] a) {
    int realSizeIntArr = 0;
    for (String e : a) {
      if (e.equals("")) {
        continue;
      } else {
        realSizeIntArr++;
      }
    }
    return realSizeIntArr;
  }

  /**
   * Take an array of numbers which are Strings and convert them into integers.

   * @param a is the array of number as string.
   * @param size is the size of the array.
   * @return the number in an array of int.
   */

  public int[] fromStringtoIntArray(String[] a, int size) {
    int[] b = new int[size];
    int iter = 0;
    for (String e : a) {
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
