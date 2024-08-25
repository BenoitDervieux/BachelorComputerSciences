package solarsystem.hierarchical;

import java.util.ArrayList;
import java.util.List;

import solarsystem.browser.Browser;
import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;

public class MoonPrinter {

  private List<HeavenlyBody> starsystem;
  private List<HeavenlyBody> moons;

  public MoonPrinter(List<HeavenlyBody> starsystem) {
    this.starsystem = starsystem;
    this.moons = new ArrayList<>();
    for(HeavenlyBody e : starsystem) {
      if (e instanceof Moon) {
        moons.add(e);
      }
    }
  }

  public void browseMoons() {
    // Instantiate the browser to send information later
    Browser browser = new Browser(this.moons);
    // Declare the type of the choice we will get
    browser.browse(false);
  }
  
}
