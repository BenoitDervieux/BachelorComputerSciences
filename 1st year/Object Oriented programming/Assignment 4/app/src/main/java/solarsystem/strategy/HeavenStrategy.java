package solarsystem.strategy;

import java.util.List;
import solarsystem.heavenlybodies.HeavenlyBody;

/**
 * Interface that implements the strategies on heavnly bodies.
 */
public interface HeavenStrategy {
  List<HeavenlyBody> addStar(List<HeavenlyBody> a);

  List<HeavenlyBody> addPlanet(List<HeavenlyBody> a);

  List<HeavenlyBody> addMoon(List<HeavenlyBody> a);

  List<HeavenlyBody> delete(List<HeavenlyBody> a);
  
  List<HeavenlyBody> modify(List<HeavenlyBody> a);
}
