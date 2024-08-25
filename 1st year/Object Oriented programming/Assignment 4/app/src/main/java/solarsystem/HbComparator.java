package solarsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import solarsystem.comparators.OrbitComparator;
import solarsystem.comparators.SizeComparator;
import solarsystem.comparators.StringComparator;
import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;
import solarsystem.heavenlybodies.Star;

/**
 * Class that compares the list of heavenlybodies.
 */
public class HbComparator {

  private List<HeavenlyBody> solarsystem;

  public HbComparator(List<HeavenlyBody> solarsystem) {
    this.solarsystem = solarsystem;
  }
  
  /**
   * Is the method that let the user choose how to compare the system.
   */
  public void comparatorMenu() {
    Scanner scann = new Scanner(System.in, "UTF-8");
    System.out.println("-- Comparator Menu --");
    int choice;
    do {
      System.out.println("1 - List the bodies by name");
      System.out.println("2 - List the bodies by size");
      System.out.println("3 - List the bodies by average radius");
      System.out.println("4 - List the bodies in a hierarchical way");
      System.out.println("0 - Back");
      System.out.print("Enter your choice: ");
      choice = scann.nextInt();

      switch (choice) {
        case 1:
          System.out.println("Here you check by name");
          List<HeavenlyBody> copyOfStC = new ArrayList<>();
          for (HeavenlyBody e : this.solarsystem) {
            copyOfStC.add(e);
          }
          // Sort the bodies by name/string using a class to compare
          Collections.sort(copyOfStC, new StringComparator());
          for (HeavenlyBody e : copyOfStC) {
            if (e instanceof Star) {
              System.out.println(e.getName() + " is a "
                  + e.getType() + ", measures " + e.getAvgRadiusInKm() + "Kms");
            } else {
              System.out.println(e.getName()
                  + " is a " + e.getType() + ", measures "
                  + e.getAvgRadiusInKm() + " Kms, and has an orbit radius of "
                  + e.getAvgOrbitRadiusInKm() + "kms.");
            }
          }
          break;
        case 2:
          System.out.println("Here you check by size");
          List<HeavenlyBody> copyOfSc = new ArrayList<>();
          for (HeavenlyBody e : this.solarsystem) {
            copyOfSc.add(e);
          }
          // Sort the system by size using a comparative class
          Collections.sort(copyOfSc, new SizeComparator());
          for (HeavenlyBody e : copyOfSc) {
            if (e instanceof Star) {
              System.out.println(e.getName() + " is a "
                   + e.getType() + ", measures " + e.getAvgRadiusInKm() + "Kms");
            } else {
              System.out.println(e.getName() + " is a "
                  + e.getType() + ", measures " + e.getAvgRadiusInKm()
                  + " Kms, and has an orbit radius of " + e.getAvgOrbitRadiusInKm()  + "kms.");
            }
          }
          break;
        case 3:
          System.out.println("Here you check by orbit");
          List<HeavenlyBody> copyOfOc = new ArrayList<>();
          for (HeavenlyBody e : this.solarsystem) {
            if (e instanceof Planet) {
              copyOfOc.add(e);
            } else if (e instanceof Moon) {
              copyOfOc.add(e);
            }
          }
          // Sort by average orbit
          Collections.sort(copyOfOc, new OrbitComparator());
          for (HeavenlyBody e : copyOfOc) {
            System.out.println(e.getName() + " is a "
                + e.getType() + ", measures " + e.getAvgRadiusInKm()
                + " Kms, and has an orbit radius of " + e.getAvgOrbitRadiusInKm()  + "kms.");
          }
          break;
        case 4:
          System.out.println("Here you check hierarchically");
          for (HeavenlyBody e : this.solarsystem) {
            if (e instanceof Star) {
              System.out.println(e.getName() + " is a " + e.getType()
                    + ", measures " + e.getAvgRadiusInKm() + "Kms");
            } else {
              if (e instanceof Planet) {
                System.out.print(" ");
              } else if (e instanceof Moon) {
                System.out.print("  ");
              }
              System.out.println(e.getName() + " is a "
                  + e.getType() + ", measures " + e.getAvgRadiusInKm()
                  + " Kms, and has an orbit radius of " + e.getAvgOrbitRadiusInKm()  + "kms.");
            }
          }
          break;
        case 0:
          break;
        default:
          System.out.println("Your choice is invalid, please try again.");
          break;
      }
    } while (choice != 0);
  } 
}
