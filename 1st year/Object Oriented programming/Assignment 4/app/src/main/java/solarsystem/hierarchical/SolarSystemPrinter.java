package solarsystem.hierarchical;

import java.util.ArrayList;
import java.util.List;

import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;

public class SolarSystemPrinter {


  private List<HeavenlyBody> solarsystem;

  public SolarSystemPrinter(List<HeavenlyBody> solarsystem) {
    this.solarsystem = new ArrayList<>();
    for(HeavenlyBody p : solarsystem) {
      this.solarsystem.add(p);
    }
  }

  public void printSolarSystem() {
    for (HeavenlyBody e : this.solarsystem) {
      if (e instanceof Planet) {
        System.out.print(" ");
        System.out.println(e);
      } else if (e instanceof Moon) {
        System.out.print("  ");
        System.out.println(e);
      } else {
        System.out.println(e);
      }
      
    }
  }
    
}
