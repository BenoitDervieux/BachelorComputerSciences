package solarsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.strategy.AddMoon;
import solarsystem.strategy.AddPlanet;
import solarsystem.strategy.AddStar;
import solarsystem.strategy.Delete;

/**
 * Class that prints the operations to do on the solarsystem.
 */
public class HbOperations {

  private List<HeavenlyBody> solarsystem;

  public HbOperations(List<HeavenlyBody> solarsystem) {
    this.solarsystem = solarsystem;
  }

  /**
   * Prints the menu for making operations on the solar system.

   * @param list of Heavenly bodies.
   * @return a list of heavenly bodies.
   */
  public List<HeavenlyBody> operationsMenu(List<HeavenlyBody> list) {
    Scanner scann = new Scanner(System.in, "UTF-8");
    System.out.println("Welcome to the operations menu");
    System.out.println("What do you want to do?");
    int choice;
    List<HeavenlyBody> bodiesToRreturn = new ArrayList<>();
    do {
      System.out.println("1 - Add a Star");
      System.out.println("2 - Add a Planet");
      System.out.println("3 - Add a Moon");
      System.out.println("4 - Delete a heavenly body");
      System.out.println("0 - Back");
      System.out.print("Enter your choice: ");
      choice = scann.nextInt();
      switch (choice) {
        case 1:
          AddStar addaStar = new AddStar();
          bodiesToRreturn = addaStar.addStar(this.solarsystem);
          break;
        case 2:
          AddPlanet addaPlanet = new AddPlanet();
          bodiesToRreturn = addaPlanet.addPlanet(this.solarsystem);
          break;
        case 3:
          AddMoon addaMoon = new AddMoon();
          bodiesToRreturn = addaMoon.addMoon(this.solarsystem);
          break;
        case 4:
          Delete deleteSomething = new Delete();
          bodiesToRreturn = deleteSomething.delete(this.solarsystem);
          break;
        case 0:
          break;
        default:
          System.out.println("Your choice is invalid, please try again.");
          break;
      }
    } while (choice != 0);
    return bodiesToRreturn;
  }

}
