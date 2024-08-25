package solarsystem.comparators;

import solarsystem.heavenlybodies.HeavenlyBody;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class method that helps to compare two strings.
 */
public class StringComparator implements Comparator<HeavenlyBody>, Serializable {

  @Override
  public int compare(HeavenlyBody o1, HeavenlyBody o2) {
    return o1.getName().compareTo(o2.getName());
  }  
}
