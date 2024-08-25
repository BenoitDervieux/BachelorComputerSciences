package view;

/**
 * Encapsulates the generic view functionality.
 */
public interface View {

  /**
   * Shows a welcome message.
   */
  void displayWelcomeMessage();

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  int getInput();

  /**
   * Displays a card.

   * @param card The card to display.
   */
  void displayCard(model.Card card);

  /**
   * Displays the cards and score of the player.

   * @param hand the player's hand.
   * @param score the player's score.
   */
  void displayPlayerHand(Iterable<model.Card> hand, int score);

  /**
   * Displays the cards and score of the dealer.

   * @param hand the dealer's score.
   * @param score the players's score.
   */
  void displayDealerHand(Iterable<model.Card> hand, int score);

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  void displayGameOver(boolean dealerIsWinner);

  /**
   * Collects the input of a player.
   */
  void collectEvents();

  /**
   * return true if the player enter 'q'.

   * @return a boolean.
   */
  boolean wantsToQuit();
  
  /**
   * return true if the player enter 'p'.

   * @return a boolean.
   */
  boolean wantsToStartNewGame();

  /**
   * return true if the player enter 'h'.

   * @return a boolean.
   */
  boolean wantsToHit();

  /**
   * return true if the player enter 's'.

   * @return a boolean.
   */
  boolean wantsToStand();

  void printDealer();

  void printPlayer();

  void jump();

  void virgule();

  void getsCardPlayer(int number);

  void getsCardDealer(int number);

  void cdisplay(int number);

}