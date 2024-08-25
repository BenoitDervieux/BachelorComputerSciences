package model.rules;

import model.Dealer;


class AmericanNewGameStrategy implements NewGameStrategy {

  public String getName() {
    return "American Game";
  }

  public boolean newGame(Dealer dealer, model.Player player) {

    dealer.processCard(true, player);

    dealer.processCard(true, dealer);

    dealer.processCard(true, player);

    dealer.processCard(false, dealer);

    return true;
  }


}