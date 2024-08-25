package model.rules;

import model.Dealer;
import model.Player;


class InternationalNewGameStrategy implements NewGameStrategy {

  public String getName() {
    return "International Game";
  }

  public boolean newGame(Dealer dealer, Player player) {

    dealer.processCard(true, player);

    dealer.processCard(true, dealer);

    dealer.processCard(true, player);

    return true;
  }

}