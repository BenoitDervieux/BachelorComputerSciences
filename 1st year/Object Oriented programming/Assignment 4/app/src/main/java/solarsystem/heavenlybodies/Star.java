package solarsystem.heavenlybodies;

import java.util.ArrayList;

/**
 * Defines the class Star which extends the class HeavenlyBody.
 */
public class Star extends HeavenlyBody {

  private ArrayList<Planet> planets = new ArrayList<>();

  /**
   * Is the constructor of the Star.

   * @param name is the name of the star.
   * @param avgRadiusInKm is the radius of the star.
   */
  public Star(String name, int avgRadiusInKm) {
    super(name, avgRadiusInKm);
  }
  
  /**
   * Add a planet.

   * @param name is the name of the planet.
   * @param avgRadiusInKm is the radius of the planet.
   * @param avgOrbitRadiusInKm is the orbit radius of the planet.
   * @return a planet.
   */
  public Planet addPlanet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {

    planets.add(new Planet(name, avgRadiusInKm, avgOrbitRadiusInKm));
    return planets.get(planets.size() - 1);
  }

  public Planet addPlanetPlain(Planet planet) {
    planets.add(planet);
    return planets.get(planets.size() - 1);
  }

  /**
   * @param name is the name of the planet one wants to delete
   */
  public void deletePlanet(String name) {
    int index = -1;
    for (Planet e : planets) {
      if (e.getName().equals(name)) {
        index = planets.indexOf(e);
      }
    }
    if (index == -1) {
      System.out.println("This is not possible to delete this element");
    } else {
      planets.remove(index);
    }
  }

  /**
   * Get a list of heavenly bodies under the star (planets and their moons).

   * @return an array of heavenly bodies.
   */
  public HeavenlyBody[] getHeavenlyBodies() {

    //This variable will help us to declare the right array size
    int number = 0;

    //Define the size of the heavely bodies by getting the size of each planet and their moons
    for (int i = 0; i < planets.size(); i++) {
      number += planets.get(i).getHeavenlyBodies().length;
    }

    //Initialize a safeArray
    HeavenlyBody[] safeCopy = new HeavenlyBody[number + 1];
    //Insert the star first in this safe copy
    safeCopy[0] = new Star(this.getName(), this.getAvgRadiusInKm());
    //Initialize an index to iterate in the safe array
    int index = 1;

    //Iterate through the planets
    for (int i = 0; i < planets.size(); i++) {
      // Initialize the planet first in the safe array
      safeCopy[index] = new Planet(planets.get(i).getName(), 
      planets.get(i).getAvgRadiusInKm(), planets.get(i).getAvgOrbitRadiusInKm());
      // Increase manually the index of the safe array
      index++;
      if (planets.get(i).getHeavenlyBodies().length > 1) {
        for (int j = 1; j < planets.get(i).getHeavenlyBodies().length; j++) {
          safeCopy[index] = new Moon(planets.get(i).getHeavenlyBodies()[j].getName(), 
          planets.get(i).getHeavenlyBodies()[j].getAvgRadiusInKm(),
          planets.get(i).getAvgOrbitRadiusInKm());
          index++;
        }
      }
    }

    return safeCopy;
  }

  /**
   * Override the toString method of. Return the star, the planets and the moons toString.
   */
  @Override
  public String toString() {
    String theString = "";
    theString += "Star: " + this.getName() + ", average radius " + this.getAvgRadiusInKm() + "km";
    return theString;
  }

  /**
   * Check the radius of a star.
   */
  @Override
  protected void checkAvgRadiusInKm(int radius) {
    if (radius < 16700) {
      throw new IllegalArgumentException("Value too low for a star");
    }
  }

  public double getAvgOrbitRadiusInKm() {
    return (Double) null;
  }

  public String getType() {
    return "Star";
  }
    
}
