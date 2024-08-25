package solarsystem.comparators;

import solarsystem.heavenlybodies.HeavenlyBody;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Class method that helps to compare two strings.
 */
public class OrbitComparator implements Comparator<HeavenlyBody>, Serializable {

  @Override
  public int compare(HeavenlyBody o1, HeavenlyBody o2) {
    if (o1.getAvgOrbitRadiusInKm() > o2.getAvgOrbitRadiusInKm()) {
      return 1;
    } else if (o1.getAvgOrbitRadiusInKm() < o2.getAvgOrbitRadiusInKm()) {
      return -1;
    } else {
      return 0;
    }
  }
}  
