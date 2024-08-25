package controller;

import model.Card;
import model.Dealer;
import model.Game;
import view.View;


/**
 * Scenario controller for playing the game.
 */
public class Player implements model.SubscriberCard {

  private View view;

  public Player(View view) {
    this.view = view;
  }

  /**
   * Runs the play use case.

   * @param game The game state.
   * @param view The view to use.
   * @return True as long as the game should continue.
   */
  public boolean play(Game game, View view) {
    view.displayWelcomeMessage();


    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());


    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }


    view.collectEvents();


    if (view.wantsToStartNewGame()) {
      game.newGame();
    } else if (view.wantsToHit()) {
      game.hit();
    } else if (view.wantsToStand()) {
      game.stand();
    } else if (view.wantsToQuit()) {
      return false;
    }

    return true;
  }

  @Override
  public void updateDealer(Dealer dealer, Iterable<Card> hand) {
    view.printDealer();
    int size = 0;
    for (Card c : hand) {
      size++;
    }
    for (int i = 0; i < size; i++) {
      view.cdisplay(i + 1);
      if (i != size - 1) {
        view.virgule();
      }
    }
    view.getsCardDealer(size);
    view.jump();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updatePlayer(model.Player player, Iterable<Card> hand) {
    view.printPlayer();
    int size = 0;
    for (Card c : hand) {
      size++;
    }
    for (int i = 0; i < size; i++) {
      view.cdisplay(i + 1);
      if (i != size - 1) {
        view.virgule();
      }
    }
    view.getsCardPlayer(size);
    view.jump();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}