package solarsystem.hierarchical;

import java.util.ArrayList;
import java.util.List;

import solarsystem.browser.Browser;
import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;
import solarsystem.heavenlybodies.Star;

public class StarPrinter {

  private List<HeavenlyBody> solarsystem;
  private List<HeavenlyBody> stars;

  public StarPrinter(List<HeavenlyBody> solarsystem) {
    this.solarsystem = solarsystem;
    this.stars = new ArrayList<>();
    for(HeavenlyBody e : this.solarsystem) {
      if (e instanceof Star) {
        this.stars.add(e);
      }
    }
  }

  /**
   * 
   */
  public void browseStars() {
    // Instantiate the browser to send information later
    Browser browser = new Browser(this.stars);
    // Declare the type of the choice we will get
    Star choice;
    // Prepare a list to receive
    List<HeavenlyBody> list_of_choices = new ArrayList<>();
    // The choice we will receive casted by what we want - look name of class
    choice = (Star) browser.browse(true);
    list_of_choices.add(choice);
    if (choice == null) {
      // here if there is a null return we just continue
    } else {
      boolean tocatch = false;
      for (int i = 0; i < solarsystem.size(); i++) {
        if (solarsystem.get(i).getName().equals(choice.getName())) {
          tocatch = true;          
        }
        if (tocatch == true) {
          if (solarsystem.get(i) instanceof Planet) {
            list_of_choices.add(solarsystem.get(i));
          } else if (solarsystem.get(i) instanceof Moon) {
            list_of_choices.add(solarsystem.get(i));
          }
        }
        if (solarsystem.get(i) instanceof Star && !solarsystem.get(i).getName().equals(choice.getName()) ) {
          tocatch = false;
        }
      }
      // then we instantiate to the next one
      SolarSystemPrinter solarSystemPrinter = new SolarSystemPrinter(list_of_choices);
      solarSystemPrinter.printSolarSystem();
    }

  }
  
}
