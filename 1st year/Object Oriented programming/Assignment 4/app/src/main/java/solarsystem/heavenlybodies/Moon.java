package solarsystem.heavenlybodies;

/**
 * Defines the class Moon which extends the class HeavenlyBody.
 */
public class Moon extends HeavenlyBody {

  private double avgOrbitRadiusInKm;

  /**
   * Is the constructor for the class Moon. Use the super constructor
   * from the heavenly body class.

   * @param name is the name of the moon.
   * @param avgRadiusInKm is the radius in km.
   * @param avgOrbitRadiusInKm the orbit radius.
   */
  public Moon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    if (avgOrbitRadiusInKm < 60) {
      throw new IllegalArgumentException("Orbit radius too small");
    } else {
      this.avgOrbitRadiusInKm = avgOrbitRadiusInKm;
    }
  }

  /**
   * Get the Orbit radius.

   * @return the orbit radius.
   */
  public double getAvgOrbitRadiusInKm() {
    return avgOrbitRadiusInKm;
  }

  /**
   * Return a special string for the moon.
   */
  @Override
  public String toString() {
    return "Moon: " + this.getName() + ", average radius " + this.getAvgRadiusInKm()
      + "km, average orbit radius " + getAvgOrbitRadiusInKm() + "km";
  }

  /**
   * Check the radius for a moon. Throw an error if not convenient.
   */
  @Override
  protected void checkAvgRadiusInKm(int radius) {
    if (radius < 6) {
      throw new IllegalArgumentException("Value too low for a moon");
    } else if (radius > 10000) {
      throw new IllegalArgumentException("Value too high for a moon");
    }
  }

  public String getType() {
    return "Moon";
  }
}
