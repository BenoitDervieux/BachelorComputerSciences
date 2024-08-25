package solarsystem.browser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import solarsystem.heavenlybodies.HeavenlyBody;

public class Browser {
  private int index;
  private List<HeavenlyBody> toBrowse;

  public Browser(List<HeavenlyBody> toBrowse) {
    this.toBrowse = new ArrayList<>();
    for (HeavenlyBody e : toBrowse) {
      this.toBrowse.add(e);
    }
    this.index = 0;
  }

  public HeavenlyBody browse(boolean a) {
    Scanner scann = new Scanner(System.in, "UTF-8");
    int choice = -1;
    do {
      for (int i = 0; i < toBrowse.size(); i++) {
        System.out.print(toBrowse.get(i).toString());
        if (i == this.index) {
          System.out.print(" <<");
        }
        System.out.println();
      }
      System.out.println("1 - Browse");
      if (a) {
        System.out.println("2 - Select");
      }
      System.out.println("0 - Back");
      choice = scann.nextInt();
      switch(choice) { 
        case 1:
        this.index = (this.index + 1) % this.toBrowse.size();
        break;
        case 2:
        if (a) {
          return toBrowse.get(index);
        } else {
          System.out.println("I did not understand your choice");
        }
        break;
        case 0:
        break;
        default:
        System.out.println("I did not understand your choice");
        break;
      }

    } while(choice != 0);

    return null;

  }
  
}
