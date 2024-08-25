package solarsystem.filewriter;


import solarsystem.heavenlybodies.HeavenlyBody;
import solarsystem.heavenlybodies.Moon;
import solarsystem.heavenlybodies.Planet;
import solarsystem.heavenlybodies.Star;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Read and write the solar systems from and on an external file.
 */
public class FileHandler implements FileWriterReader {

  @Override
    public List<HeavenlyBody> setUpTheSystem(List<HeavenlyBody> system) {
    // Get the solarsystems.data file. To put in the general folder
    String filename = Paths.get("").toAbsolutePath().resolve("solarsystems.data").toString();
    // On windows it replaces the "\\" and to have a working path
    filename = filename.replace("\\", "/");
    StringBuilder bufferFilename = new StringBuilder();
    for (int i = 0; i < filename.length(); i++) {
      char currentCharacter = filename.charAt(i);
      bufferFilename.append(currentCharacter);
      if (i == 2) {
        bufferFilename.append("/");
      }
    }
    try {
      FileInputStream file = new FileInputStream(bufferFilename.toString());
      InputStreamReader input = new InputStreamReader(file, "UTF-8");
      BufferedReader br = new BufferedReader(input);
      String line = br.readLine();
      // Regular expression to read the .data file
      Pattern patternPm = Pattern.compile("(^-{0,2})(\\w+):(\\d+):(\\d+\\.*\\d+)");
      Pattern patternS = Pattern.compile("(^-{0,2})(\\w+):(\\d+)");
      int index = 0;
      while (line != null) {
        Matcher matcherPm = patternPm.matcher(line);
        Matcher matcherS = patternS.matcher(line);
        if (matcherPm.find()) {
          if (matcherPm.group(1).equals("-")) {
            for (int i = index - 1; i > -1; i--) {
              if (system.get(i) instanceof Star) {
                Planet planet = new Planet(matcherPm.group(2), 
                                          Integer.parseInt(matcherPm.group(3)),
                                          Double.parseDouble(matcherPm.group(4)));
                ((Star) system.get(i)).addPlanetPlain(planet);
                system.add(planet);
                break;
              }
            }
          } else if (matcherPm.group(1).equals("--")) {
            for (int j = index - 1; j > -1; j--) {
              if (system.get(j) instanceof Planet) {
                Moon moon = new Moon(matcherPm.group(2), 
                                Integer.parseInt(matcherPm.group(3)), 
                                Double.parseDouble(matcherPm.group(4)));
                ((Planet) system.get(j)).addMoonPlain(moon);
                system.add(moon);
                break;
              }
            }
          } 
        } else if (matcherS.find()) {
          Star star = new Star(matcherS.group(2), Integer.parseInt(matcherS.group(3)));
          system.add(star);
        }
        line = br.readLine();
        index++;
      }
      br.close();
    } catch (IOException e) {
      System.out.println("Could not find the file");;
    }
    return system;
  }


  @Override
    public void writeSolarSystem(List<HeavenlyBody> system) {
    String filename = Paths.get("").toAbsolutePath().resolve("solarsystems.data").toString();
    filename = filename.replace("\\", "/");
    StringBuilder bufferFilename = new StringBuilder();
    for (int i = 0; i < filename.length(); i++) {
      char currentCharacter = filename.charAt(i);
      bufferFilename.append(currentCharacter);
      if (i == 2) {
        bufferFilename.append("/");
      }
    }
    File file = new File(bufferFilename.toString());
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        FileOutputStream  writer = new FileOutputStream(file);
        OutputStreamWriter output = new OutputStreamWriter(writer, "UTF-8");
        String toCopy = "";
        // Writing into the file according to the way pattern it supposed to be
        for (HeavenlyBody e : system) {
          if ( e instanceof Star) {
            toCopy = e.getName() + ":" + e.getAvgRadiusInKm() + "\n";
            output.write(toCopy);
          } else if ( e instanceof Planet ) {
            toCopy = "-" + e.getName() + ":" + e.getAvgRadiusInKm()
              + ":" + ((Planet) e).getAvgOrbitRadiusInKm() + "\n";
            output.write(toCopy);
          } else if (e instanceof Moon) {
            toCopy = "--" + e.getName() + ":" + e.getAvgRadiusInKm()
                + ":" + ((Moon) e).getAvgOrbitRadiusInKm() + "\n";
              output.write(toCopy);
          }
        }
        // Close everything
        output.close();
        writer.close();
      } catch (IOException e) {
        System.out.println("Could not write into the file");;
      }
    }
  }


  @Override
  public void clearFile() {
    // TODO Auto-generated method stub
    System.out.println("Method not implemented");
  }

  /*@Override
  public void clearFile() {
    // This is the method to get the path directly without hardcodimg
    // There is a problem for windows so the "\" should be replaced
    String filename = Paths.get("").toAbsolutePath().resolve("solarsystems.data").toString();
    filename = filename.replace("\\", "/");
    StringBuilder bufferFilename = new StringBuilder();
    for (int i = 0; i < filename.length(); i++) {
      char currentCharacter = filename.charAt(i);
      bufferFilename.append(currentCharacter);
      if (i == 2) {
        bufferFilename.append("/");
      }
    }
    File file = new File(bufferFilename.toString());
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        // intialize the file
        FileOutputStream  writer = new FileOutputStream(file);
        OutputStreamWriter output = new OutputStreamWriter(writer, "UTF-8");
        // We clear the whole file by writing an empty string
        output.write("");
        output.close();
      } catch (IOException e) {
        System.out.println("An error occured while clearing the file");
      }
    }   
  }*/
}
