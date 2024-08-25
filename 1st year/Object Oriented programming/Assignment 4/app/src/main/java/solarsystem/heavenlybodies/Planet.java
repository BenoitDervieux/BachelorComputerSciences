package solarsystem.heavenlybodies;

import java.util.ArrayList;

/**
 * Defines the class Planet which extends the class HeavenlyBody.
 */
public class Planet extends HeavenlyBody {

  private double avgOrbitRadiusInKm;
  private ArrayList<Moon> moons = new ArrayList<>();
  
  /**
   * Is the constructor for the class Planet. Use the super constructor
   * from the heavenly body class.

   * @param name is the name of the planet.
   * @param avgRadiusInKm is the radius in km.
   * @param avgOrbitRadiusInKm the orbit radius.
   */
  public Planet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    if (avgOrbitRadiusInKm < 18000) {
      throw new IllegalArgumentException("Orbit radius too small");
    } else {
      this.avgOrbitRadiusInKm = avgOrbitRadiusInKm;
    }
  }

  /**
   * Add a moon in a solar system.

   * @param name is the name of the moon.
   * @param avgRadiusInKm is the radius of the moon.
   * @param avgOrbitRadiusInKm is the orbit radius.
   * @return a moon.
   */
  public Moon addMoon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    if (avgRadiusInKm > this.getAvgRadiusInKm() / 2) {
      System.out.println("Test de l'erreur " + this.getAvgRadiusInKm() / 2);
      throw new IllegalArgumentException("A moon cannot be larger than the half of a planet");
    } else {
      moons.add(new Moon(name, avgRadiusInKm, avgOrbitRadiusInKm));
      return moons.get(moons.size() - 1);
    }
  }

  public Moon addMoonPlain(Moon moon) {
    moons.add(moon);
    return moons.get(moons.size() - 1);
  }

  public void deleteMoon(String name) {
    int index = -1;
    for (Moon e : moons) {
      if (e.getName().equals(name)) {
        index = moons.indexOf(e);
      }
    }
    if (index == -1) {
      System.out.println("This is not possible to delete this element");
    } else {
      moons.remove(index);
    }
  }

  /**
   * Return the orbit radius.

   * @return the orbit radius.
   */
  public double getAvgOrbitRadiusInKm() {
    return avgOrbitRadiusInKm;
  }

  /**
   * List all the heavenly bodies of the planet.

   * @return an array of heavenly bodies (moons).
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    // The size of the array should be size of moon plus 1 as we count the planet
    HeavenlyBody[] safeCopy = new HeavenlyBody[moons.size() + 1];

    // First add the planet
    safeCopy[0] = new Planet(this.getName(), this.getAvgRadiusInKm(), this.getAvgOrbitRadiusInKm());

    //Then iterate through the moons to add them
    for (int i = 0; i < moons.size(); i++) {
      safeCopy[i + 1] = new Moon(moons.get(i).getName(), moons.get(i).getAvgRadiusInKm(),
      moons.get(i).getAvgOrbitRadiusInKm());
    }
    return safeCopy;
  }

  /**
   * Override the toString method of. Return the planet and the moons toString.
   */
  @Override
  public String toString() {
    String theString = "";
    theString += "Planet: " + this.getName() + ", average radius " + this.getAvgRadiusInKm() + "km, average orbit radius "
    + this.getAvgOrbitRadiusInKm() + "km";
    return theString;
  }

  /**
   * Check the radius of a planet.
   */
  @Override
  protected void checkAvgRadiusInKm(int radius) {
    if (radius < 2000) {
      throw new IllegalArgumentException("Value too low for a moon");
    } else if (radius > 200000) {
      throw new IllegalArgumentException("Value too high for a moon");
    }
  }

  public String getType() {
    return "Planet";
  }
    
}
