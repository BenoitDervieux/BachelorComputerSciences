package model.rules;

import model.Player;

/**
 * Rule interface that encapsulates when a player (the dealer) wins.
 */
public interface WinGameStrategy {

  /**
  * Checks if the player has won.

  * @param dealer the player to check.
  * @return True if the dealer has won, false otherwise.
  */
  boolean isDealerWinner(Player dealer, Player player);

  /**
   * Gets the name of the Win strategy.

   * @return a string with the name of the strategy.
   */
  String getName();
    
}
