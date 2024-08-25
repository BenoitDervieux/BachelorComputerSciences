package model.rules;

import model.Player;

/**
 * This class defines the normal startegy for winning a game
 * as if the dealer has the same score as the player
 * the dealer wins.
 */
public class NormalWinStrategy implements WinGameStrategy {

  private static final int maxScore = 21;

  public String getName() {
    return "The Dealer wins if equal.";
  }

  @Override
    public boolean isDealerWinner(Player dealer, Player player) {
    if (player.calcScore() > maxScore) {
      return true;
    } else if (dealer.calcScore() > maxScore) {
      return false;
    }
    return dealer.calcScore() >= player.calcScore();   
  }

}
