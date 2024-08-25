package solarsystem.filewriter;

import java.util.List;

import solarsystem.heavenlybodies.HeavenlyBody;

/**
 * This is the interface to create IO classes and methods.
 */
public interface FileWriterReader {
  public List<HeavenlyBody> setUpTheSystem(List<HeavenlyBody> system);

  public void writeSolarSystem(List<HeavenlyBody> system);

  public void clearFile();
 
}
