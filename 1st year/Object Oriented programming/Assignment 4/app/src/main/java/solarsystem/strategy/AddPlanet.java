package solarsystem.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Planet;
import solarsystem.heavenlybodies.Star;

public class AddPlanet implements HeavenStrategy {

  @Override
  public List<HeavenlyBody> addStar(List<HeavenlyBody> bodies) {
    return null;
  }

  @Override
  public List<HeavenlyBody> addPlanet(List<HeavenlyBody> bodies) {
    // Initiate the scanner
    Scanner scann = new Scanner(System.in, "UTF-8");
    // Ask under which star you want to add the planet
    // and get the name of the star
    System.out.println("Under what star do you want to add this planet?");
    String star_name = scann.nextLine();
    // initialize a boolean to see if the name exists
    boolean name_exists = false;
    // iterate through the bodies to check if the name really exists
    for (int i = 0; i < bodies.size(); i++) {
      if (bodies.get(i).getName().equals(star_name) && bodies.get(i) instanceof Star) {
        name_exists = true;
      }
    }
    if (name_exists == false) {
      // if the name if not found, return the same bodies
      // otherwise continue
      System.out.println("No star with this name");
      return bodies;
    }
    // Ask for the name of the Planet
    System.out.println("what is the name of your Planet");
    String name_of_planet = scann.nextLine();
    // Iterate through the bodies to check if the name if already taken
    // If yes returns the bodies otherwise continue
    for (HeavenlyBody e : bodies) {
      if (e.getName().equals(name_of_planet)) {
        System.out.println("This name is taken");
        return bodies;
      }
    }

    // Ask for the radius of the Planet
    int radius_of_planet;
    do {
      // While loop until the user input the correct values
      System.out.println("what is the radius of your Planet");
      radius_of_planet = scann.nextInt();
      // print the cause of not acceptin the values
      if (radius_of_planet < 2000) {
        System.out.println("The radius is too small, it should be over 2 000km");
      } else if (radius_of_planet > 200000) {
        System.out.println("The radius is too big, it should be under 200 000km");
      }
    } while (radius_of_planet < 2000 || radius_of_planet > 200000);
    // Ask for the orbit radius of the Planet
    int orbit_radius_planet;
    do {
      // While loop until the user input the correct values
      System.out.println("what is the orbit radius of your Planet");
      // print the cause of not acceptin the values
      orbit_radius_planet = scann.nextInt();
      if (orbit_radius_planet < 18000) {
        System.out.println("The orbit radius is too small, it should be higher than 18 000");
      }
    } while(orbit_radius_planet < 18000);
    Planet planet = new Planet(name_of_planet, orbit_radius_planet, orbit_radius_planet);
    int index = - 1;
    // Iterate through all the bodies, look for the name and add the Planet
    for (HeavenlyBody e: bodies) {
      if (e instanceof Star) {
        if (e.getName().equals(star_name)) {
          ((Star) e).addPlanet(name_of_planet, radius_of_planet, orbit_radius_planet);
          index = bodies.indexOf(e);
          break;
        }
      }
    }
    int indexToAdd = -1;
    if (index != -1) {
      for (int i = index + 1; i < bodies.size(); i++) {
        if (bodies.get(i) instanceof Star) {
          indexToAdd = i;
        }
      }
    }
    if (indexToAdd == -1) {
      bodies.add(planet);
    } else if (indexToAdd == bodies.size()){
      bodies.add(planet);
    } else {
      bodies.add(indexToAdd, planet);
    }
    return bodies;
  }

  @Override
  public List<HeavenlyBody> addMoon(List<HeavenlyBody> bodies) {
    return null;
  }

  @Override
  public List<HeavenlyBody> delete(List<HeavenlyBody> bodies) {
    return null;
  }

  @Override
  public List<HeavenlyBody> modify(List<HeavenlyBody> bodies) {
    return null;
  }
  
}
