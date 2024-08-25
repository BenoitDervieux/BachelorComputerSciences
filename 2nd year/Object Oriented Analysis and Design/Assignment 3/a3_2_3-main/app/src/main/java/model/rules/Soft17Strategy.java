package model.rules;

import model.Card;
import model.Player;

/**
 * This is the class that implements the soft17 strategy
 * which consists in giving an Ace a value of 1 
 * for the dealer.
 */
public class Soft17Strategy implements HitStrategy {

  public String getName() {
    return "Soft 17 hit strategy.";
  }

  @Override
  public boolean doHit(Player dealer) {
    if (dealer.calcScore() == 17) {
      for (Card c : dealer.getHand()) {
        if (c.getValue() == Card.Value.Ace) {
          return true;
        }
      }
    }
    return dealer.calcScore() < 17;
  }

}
