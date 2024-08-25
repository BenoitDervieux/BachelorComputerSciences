package solarsystem;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import solarsystem.filewriter.FileHandler;
import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.hierarchical.StarPrinter;

/**
 * Class for the principal menu.
 */
public class HbPrincipal {

  private List<HeavenlyBody> solarsystem;

  public HbPrincipal(List<HeavenlyBody> solarsystem) {
    this.solarsystem = solarsystem;
  }

  /**
   * Display the operations on the principal menu.

   * @throws SecurityException is a security exception.
   * @throws IOException is a IO exception.
   */
  public void principalMenu() throws SecurityException, IOException {
    Scanner scann = new Scanner(System.in, "UTF-8");
    System.out.println("-- Principal Menu --");
    int choice;
    do {
      System.out.println("1 - Make operations on the solar system");
      System.out.println("2 - Select a special solar system");
      System.out.println("3 - List the solar system in different ways");
      System.out.println("0 - Quit the application");
      System.out.print("Enter your choice: ");
      choice = scann.nextInt();

      switch (choice) {
        case 1:
          HbOperations operation = new HbOperations(solarsystem);
          operation.operationsMenu(this.solarsystem);
          break;
        case 2:
          StarPrinter printStars = new StarPrinter(solarsystem);
          printStars.browseStars();
          break;
        case 3:
          HbComparator comparator = new HbComparator(solarsystem);
          comparator.comparatorMenu();
          break;
        case 0:
          FileHandler writeOnFile = new FileHandler();
          writeOnFile.writeSolarSystem(this.solarsystem);
          break;
        default:
          System.out.println("Your choice is invalid, please try again.");
          break;
        }
    } while (choice != 0);
  }
  
}
