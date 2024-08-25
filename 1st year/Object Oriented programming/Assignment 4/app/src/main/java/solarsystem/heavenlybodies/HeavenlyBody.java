package solarsystem.heavenlybodies;

/**
 * Defines the class HeavenlyBody.
 */
public abstract class HeavenlyBody {

  private String name;
  private int avgRadiusInKm;

  /**
   * This is the constructor of the class.

   * @param name is the name of the HeavenlyBody.
   * @param avgRadiusInKm is the radius in km of the star.
   */
  protected HeavenlyBody(String name, int avgRadiusInKm) {
    setName(name);
    setAvgRadiusInKm(avgRadiusInKm);
  }

  /**
   * Return the name of the heavenlybody.

   * @return the name of the class.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the Heavenly Body.

   * @param newName is the name of the Heavenly body.
   */
  private void setName(String newName) {
    if (newName == null || newName.equals("")) {
      throw new IllegalArgumentException("Please enter a real name");
    } else {
      name = newName;
    }
  }

  /**
   * Return the radius of the heavenly body.

   * @return the radius.
   */
  public int getAvgRadiusInKm() {
    return avgRadiusInKm;
  }

  /**
   * Set the radius.

   * @param radius is the radius to set.
   */
  private void setAvgRadiusInKm(int radius) {
    checkAvgRadiusInKm(radius);
    avgRadiusInKm = radius;
  }

  /**
   * Check the size of the radius.

   * @param radius is the radius to check.
   */
  protected void checkAvgRadiusInKm(int radius) {
  }

  /**
   * Get the Orbit radius.

   * @return the orbit radius.
   */
  public double getAvgOrbitRadiusInKm() {
    return (Double) null;
  }

  public String getType() {
    return "";
  }
}
