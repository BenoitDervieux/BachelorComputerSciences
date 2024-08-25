package solarsystem.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;
import solarsystem.heavenlybodies.Star;

public class Delete implements HeavenStrategy {

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
    return null;
  }

  @Override
  public List<HeavenlyBody> delete(List<HeavenlyBody> bodies) {
    System.out.println("What's the name of the body you want to delete?");
    Scanner scann = new Scanner(System.in, "UTF-8");
    String name_to_delete = scann.nextLine();
    boolean exists = false;
    for (HeavenlyBody e : bodies) {
      if (e.getName().equals(name_to_delete)) {
        exists = true;
      }
    }
    if (exists == false) {
      System.out.println("There is no heavenly body with this name");
      return null;
    }
    // Here we will delete the targeted body
    for (HeavenlyBody p : bodies) {
      if (p.getName().equals(name_to_delete)) {

        // deletion for the moon
        if (p instanceof Moon) {
          for (int i = bodies.indexOf(p); i > -1; i--) {
            if (bodies.get(i) instanceof Planet) {
              ((Planet) bodies.get(i)).deleteMoon(name_to_delete);
            }
          }
          bodies.remove(bodies.indexOf(p));
          break;
        }

        //deletion for the Planet
        if (p instanceof Planet ) {
          // Delete the planet in the 
          for (int i = bodies.indexOf(p); i > -1; i--) {
            if (bodies.get(i) instanceof Star) {
              ((Star) bodies.get(i)).deletePlanet(p.getName());
              break;
            }
          }
          int where = bodies.indexOf(p);
          bodies.remove(where);
          while(bodies.get(where) instanceof Moon) {
          bodies.remove(where);
          };
          break;
        }

        if (p instanceof Star) {
          int index = bodies.indexOf(p);
          bodies.remove(index);
          for (;;) {
            if (bodies.size() > index) {
              if (bodies.get(index) instanceof Moon 
              || bodies.get(index) instanceof Planet) {
                bodies.remove(index);
              }
            } else {
              break;
            }
          }
          break;
        }
      }
    }
    // Remake the list using only the stars from our list
    // Initialize a copy an array of bodies to return
    List<HeavenlyBody> bodies_to_return = new ArrayList<>();
    // Iterate through all the bodies
    // If they are stars, ask for the getHeavenlyBodies by also casting as a Star 
    // and controlling the instance of a Star
    for (HeavenlyBody e : bodies) {
      // for each star create a buffer which is an array of heavenly bodies 
      // and from that add it to the new heavenly bodies list to return
      if (e instanceof Star) {
        HeavenlyBody[] buffer;
        buffer = ((Star) e).getHeavenlyBodies();
        for (HeavenlyBody p : buffer) {
          bodies_to_return.add(p);
        }
      }
    }
    // return the modified list
    return bodies_to_return;
  }

  @Override
  public List<HeavenlyBody> modify(List<HeavenlyBody> bodies) {
    return null;
  }
  
}
