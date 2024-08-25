package model.rules;

import model.Player;

/**
 * This is the class for the player win strategty
 * where the player wins when having the same
 * score as the dealer.
 */
public class PlayerWinGameStrategy implements WinGameStrategy {
  private static final int maxScore = 21;

  public String getName() {
    return "The Player wins if equals.";
  }

  @Override
    public boolean isDealerWinner(Player dealer, Player player) {

    if (player.calcScore() > maxScore) {
      return true;
    } else if (dealer.calcScore() > maxScore) {
      return false;
    }
    return dealer.calcScore() > player.calcScore();      
  }

}
