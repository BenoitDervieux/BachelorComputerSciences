package view;

import model.Visiteur;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.WinGameStrategy;

/**
 * Implements an english console view.
 */
public class EnglishView extends TerminalView implements View, Visiteur {

  private String[] ith = { "", "first", "second", "third", "fourth", "fifth", "sixth", 
      "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth" };

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    /*for (int i = 0; i < 5; i++) {
      System.out.print("\n");
    }*/
    System.out.println("Hello Black Jack World");
    System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
  }

  public void displayCard(model.Card card) {
    System.out.println("" + card.getValue() + " of " + card.getColor());
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Player", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Dealer", hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Has: ");
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Score: " + score);
    System.out.println("");
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("GameOver: ");
    if (dealerIsWinner) {
      System.out.println("Dealer Won!");
    } else {
      System.out.println("You Won!");
    }

  }

  @Override
  public void visit(HitStrategy hitStrategy) {
    System.out.println("Your hit strategy: " + hitStrategy.getName());
  }

  @Override
  public void visit(WinGameStrategy winGameStrategy) {
    System.out.println("Your win strategy: " + winGameStrategy.getName());
  }

  @Override
  public void visit(NewGameStrategy newGameStrategy) {
    System.out.println("Your new game strategy: " + newGameStrategy.getName());
  }

  /**
   * Prints the dealer.
   */
  public void printDealer() {
    System.out.print("Dealer: ");

  }

  /**
   * Prints the player.
   */
  public void printPlayer() {
    System.out.print("Player: ");

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
   * Prints the number of card the player gets.
   */
  public void getsCardPlayer(int number) {
    System.out.print(" (player gets " + ith[number] + " card) ");
  }

  /**
   * Prints the number of card the dealer gets.
   */
  public void getsCardDealer(int number) {
    System.out.print(" (dealer gets " + ith[number] + " card) ");

  }

  /**
   * Prints the card C number.
   */
  public void cdisplay(int number) {
    System.out.print("c" + number);
  }

}
