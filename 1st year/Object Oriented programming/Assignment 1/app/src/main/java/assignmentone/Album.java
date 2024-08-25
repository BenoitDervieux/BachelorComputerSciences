package assignmentone;


/**
 * This class creates Album.
 */
public class Album {
  private String name;
  private int releaseYear;
  private String artist;

  /**
   * Constructor for the class Album.

   * @param n is the name of the album.
   * @param y is the year of release of the album.
   * @param a is the name of the artist.
   */
  public Album(String n, int y, String a) {
    setName(n);
    setYear(y);
    setArtist(a);
  }

  /**
   * Set the name of the album. Set "No name" if the string is inferior to 4 characters
   * or if it has a null value.

   * @param n is the name of the album.
   */
  public void setName(String n) {
    if (n == null || n.length() < 4) {
      name = "No name";
    } else {
      name = n;
    }
  }
    
  public String getName() {
    return name;
  }

  /**
   * Set the year of the album. The year needs to be between 1800 and 2030 otherwise
   * it is set at -1;

   * @param y is the year.
   */
  public void setYear(int y) {
    if (y > 2029 || y <= 1800) {
      releaseYear = -1;
    } else {
      releaseYear = y;
    }
  }

  public int getYear() {
    return releaseYear;
  }

  /**
   * Set the name of the artist. Set "No artist" if the name is inferior than 4 characters.

   * @param a is the name of the artist.
   */
  public void setArtist(String a) {
    if (a == null || a.length() < 4) {
      artist = "No artist";
    } else {
      artist = a;
    } 
  }

  public String getArtist() {
    return artist;
  }

}
