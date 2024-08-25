package solarsystem.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;
import solarsystem.heavenlybodies.Star;

public class AddMoon implements HeavenStrategy {

  @Override
  public List<HeavenlyBody> addStar(List<HeavenlyBody> bodies) {
    return null;
  }

  @Override
  public List<HeavenlyBody> addPlanet(List<HeavenlyBody> bodies) {
    return null;
  }

  @Override
  public List<HeavenlyBody> addMoon(List<HeavenlyBody> bodies) {
    // Initiate the scanner
    Scanner scann = new Scanner(System.in, "UTF-8");
    // Ask under which plane you want to add the moon
    // and get the name of the star
    System.out.println("Under what Planet do you want to add this Moon?");
    String planet_name = scann.nextLine();
    // initialize a boolean to see if the name exists
    boolean name_exists = false;
    // iterate through the bodies to check if the name really exists
    int limit = 0;
    for (HeavenlyBody e: bodies) {
      if (e.getName().equals(planet_name) && e instanceof Planet) {
        name_exists = true;
        limit = e.getAvgRadiusInKm();
      }
    }
    if (name_exists == false) {
      // if the name if not found, return the same bodies
      // otherwise continue
      System.out.println("No star with this name");
      return bodies;
    }
    // Ask for the name of the Moon
    System.out.println("what is the name of your Moon");
    String name_of_moon = scann.nextLine();
    // Iterate through the bodies to check if the name if already taken
    // If yes returns the bodies otherwise continue
    for (HeavenlyBody e : bodies) {
      if (e.getName().equals(name_of_moon)) {
        System.out.println("This name is taken");
        return bodies;
      }
    }
    // Ask for the radius of the Moon
    int radius_of_moon;
    do {
      // While loop until the user input the correct values
      System.out.println("what is the radius of your Moon");
      radius_of_moon = scann.nextInt();
      // print the cause of not acceptin the values
      if (radius_of_moon < 6) {
        System.out.println("The radius is too small, it should be higher than 6km");
      } else if (radius_of_moon > 10000) {
        System.out.println("The radius is too big, it should be under 10 000km");
      } else if (radius_of_moon > limit/2) {
        int limit_to_print = limit/2;
        System.out.println("The radius is too big, it should be under " + limit_to_print +"km");
      }
    } while (radius_of_moon < 6 || radius_of_moon > 10000 || radius_of_moon > (limit/2));
    // Ask for the orbit radius of the Planet
    int orbit_radius_moon;
    do {
      // While loop until the user input the correct values
      System.out.println("what is the orbit radius of your Planet");
      // print the cause of not acceptin the values
      orbit_radius_moon = scann.nextInt();
      if (orbit_radius_moon < 60) {
        System.out.println("The orbit radius is too small");
      }
    } while(orbit_radius_moon < 60);
    Moon moon = new Moon(name_of_moon, radius_of_moon, orbit_radius_moon);
    int index = - 1;
    // Iterate through all the bodies, look for the name and add the Planet
    for (HeavenlyBody e: bodies) {
      if (e instanceof Planet) {
        if (e.getName().equals(planet_name)) {
          ((Planet) e).addMoon(name_of_moon, radius_of_moon, orbit_radius_moon);
          index = bodies.indexOf(e);
          break;
        }
      }
    }
    int indexToAdd = -1;
    if (index != -1) {
      for (int i = index + 1; i < bodies.size(); i++) {
        if (bodies.get(i) instanceof Planet || bodies.get(i) instanceof Star) {
          indexToAdd = i;
        }
      }
    }
    if (indexToAdd == -1) {
      bodies.add(moon);
    } else if (indexToAdd == bodies.size()){
      bodies.add(moon);
    } else {
      bodies.add(indexToAdd, moon);
    }
    // return the modified list
    return bodies;
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
