package solarsystem.hierarchical;

import java.util.ArrayList;
import java.util.List;

import solarsystem.browser.Browser;
import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;

public class PlanetPrinter {
  
  private List<HeavenlyBody> starsystem;
  private List<HeavenlyBody> planets;

  public PlanetPrinter(List<HeavenlyBody> starsystem) {
    this.starsystem = new ArrayList<>();
    for(HeavenlyBody p : starsystem) {
      this.starsystem.add(p);
    }
    this.planets = new ArrayList<>();
    for(HeavenlyBody e : this.starsystem) {
      if (e instanceof Planet) {
        this.planets.add(e);
      }
    }
  }

  public void browsePlanets() {
    // Instantiate the browser to send information later
    Browser browser = new Browser(this.planets);
    // Declare the type of the choice we will get
    Planet choice;
    // Prepare a list to receive
    List<HeavenlyBody> list_of_choices = new ArrayList<>();
    // The choice we will receive casted by what we want - look name of class
    choice = (Planet) browser.browse(true);
    if (choice == null) {
      // here if there is a null return we just continue
    } else {
      boolean tocatch = false;
      for (int i = 0; i < this.starsystem.size(); i++) {
        if (this.starsystem.get(i).getName().equals(choice.getName())) {
          tocatch = true;          
        }
        if (tocatch == true) {
            if (this.starsystem.get(i) instanceof Moon) {
            list_of_choices.add(this.starsystem.get(i));
          }
        }
        if (this.starsystem.get(i) instanceof Planet && !this.starsystem.get(i).getName().equals(choice.getName()) ) {
          tocatch = false;
        }
      }
      // then we instantiate to the next one
      MoonPrinter printMoons = new MoonPrinter(list_of_choices);
      printMoons.browseMoons();
    }
  }
}
