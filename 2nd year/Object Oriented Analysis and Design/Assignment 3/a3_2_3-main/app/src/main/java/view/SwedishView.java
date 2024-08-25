package view;

import model.Visiteur;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.WinGameStrategy;

/**
 * Implements a Swedish console view.
 */
public class SwedishView extends TerminalView implements View, Visiteur {

  private String[] ith = { "", "första", "andra", "tredje", "fjärde", "femte", "sjätte",
      "sjunde", "årtonde", "nionde", "tionde", "elfte", "tolfte", "trettonde" };

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }

    System.out.println("Hej Black Jack Världen");
    System.out.println("----------------------");
    System.out.println("Skriv 'p' för att Spela, 'h' för nytt kort, 's'"
        + " för att stanna 'q' för att avsluta\n");
  }

  /**
   * Displays a card.

   * @param card The card to display.
   */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Dolt Kort");
    } else {
      String[] colors = { "Hjärter", "Spader", "Ruter", "Klöver" };
      String[] values = { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio",
                          "knekt", "dam", "kung", "ess" };
      System.out.println("" + colors[card.getColor().ordinal()]
          + " " + values[card.getValue().ordinal()]);
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Spelare", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Croupier", hand, score);
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("Slut: ");
    if (dealerIsWinner) {
      System.out.println("Croupiern Vann!");
    } else {
      System.out.println("Du vann!");
    }
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Har: " + score);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Poäng: " + score);
    System.out.println("");
  }

  @Override
  public void visit(HitStrategy hitStrategy) {
    System.out.println("Din hit strategi: " + hitStrategy.getName());
  }

  @Override
  public void visit(WinGameStrategy winGameStrategy) {
    System.out.println("Din vin strategi: " + winGameStrategy.getName());
  }

  @Override
  public void visit(NewGameStrategy newGameStrategy) {
    System.out.println("Din nya spel strategi: " + newGameStrategy.getName());
  }

  /**
   * Prints the dealer.
   */
  public void printDealer() {
    System.out.print("Croupier: ");

  }

  /**
   * Prints the player.
   */
  public void printPlayer() {
    System.out.print("Spelare: ");

  }
  
  /**
   * Jump a line.
   */
  public void jump() {
    System.out.println("");

  }

  /**
   * Prints a comma.
   */
  public void virgule() {
    System.out.print(", ");
  }

  /**
   * Prints the Number of card for the player.
   */
  public void getsCardPlayer(int number) {
    System.out.print("(Spelare får " + ith[number] + " kort) ");
  }

  /**
   * Prints the number of card for the dealer.
   */
  public void getsCardDealer(int number) {
    System.out.print("(Croupier får " + ith[number] + " kort) ");

  }

  /**
   * Prints the card C number.
   */
  public void cdisplay(int number) {
    System.out.print("c");
  }
}
