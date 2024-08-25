package solarsystem.strategy;

import java.util.List;
import java.util.Scanner;

import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Star;

public class AddStar implements HeavenStrategy {

  @Override
  public List<HeavenlyBody> addStar(List<HeavenlyBody> bodies) {
    Scanner scann = new Scanner(System.in, "UTF-8");
    System.out.println("What's the name of the star you want to add?");
    String name = scann.nextLine();
    for (HeavenlyBody e : bodies) {
      if (e.getName().equals(name)) {
        System.out.println("This name is already taken");
        return bodies;
      }
    }
    int radius = 0;
    do {
      System.out.println("What's the radius of the star you want to add?");
      radius = scann.nextInt();
      if (radius < 16700) {
        System.out.println("The radius should be bigger than 16 700km");
      }

    } while(radius < 16700);
    
    Star newStar = new Star(name, radius);
    bodies.add(newStar);
    return bodies;
  }

  @Override
  public List<HeavenlyBody> addPlanet(List<HeavenlyBody> bodies) {
    return bodies;
  }

  @Override
  public List<HeavenlyBody> addMoon(List<HeavenlyBody> bodies) {
    return bodies;
  }

  @Override
  public List<HeavenlyBody> delete(List<HeavenlyBody> bodies) {
    return bodies;
  }

  @Override
  public List<HeavenlyBody> modify(List<HeavenlyBody> bodies) {
    return bodies;
  }
  
}
