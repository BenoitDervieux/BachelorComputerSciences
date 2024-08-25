package model.rules;

import model.Player;

class BasicHitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  public String getName() {
    return "Basic hit Strategy";
  }

  public boolean doHit(Player dealer) {
    return dealer.calcScore() < hitLimit;
  }
}