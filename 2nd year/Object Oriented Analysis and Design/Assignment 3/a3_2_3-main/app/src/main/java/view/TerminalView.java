package view;

/**
 * Common view for English and Swedish.
 */
public class TerminalView {

  private int event;

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  public void collectEvents() {
    event = getInput();
  }

  public boolean wantsToQuit() {
    return event == 'q';
  }

  public boolean wantsToStartNewGame() {
    return event == 'p';
  }

  public boolean wantsToHit() {
    return event == 'h';
  }

  public boolean wantsToStand() {
    return event == 's';
  }
    
}
