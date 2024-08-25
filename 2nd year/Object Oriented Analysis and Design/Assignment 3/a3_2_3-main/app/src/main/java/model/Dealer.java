package model;

import java.util.ArrayList;
import model.rules.AbstractRulesFactory;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.WinGameStrategy;

/**
 * Represents a dealer player that handles the deck of cards and runs the game using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private WinGameStrategy winGameRule;
  private ArrayList<SubscriberCard> subscribers;  

  /**
   * Initializing constructor.

   * @param a An abstractfactory that creates the rules to use.
   */
  public Dealer(AbstractRulesFactory a) {

    newGameRule = a.createNewGameStrategy();
    hitRule = a.createHitStrategy();
    winGameRule = a.createWinGameStrategy();
    subscribers = new ArrayList<SubscriberCard>();

  }

  /**
   * Starts a new game if the game is not currently under way.

   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(this, player);
    }
    return false;
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.

   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      processCard(true, player);
      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param player The player to check agains.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    return winGameRule.isDealerWinner(this, player);
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.

   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * The player has choosen to take no more cards, it is the dealers turn.
   */
  public boolean stand() {
    if (deck != null) {
      showHand();
      while (hitRule.doHit(this) == true) {
        processCard(true, this);
      }
      return true;
    }
    return false;
  }

  /**
   * This method adds a card to either the player or the dealer's hand.

   * @param show is to show or hide the card.
   * @param player is the player to take the card from.
   */
  public void processCard(boolean show, Player player) {
    Card.Mutable c = deck.getCard();
    c.show(show);
    player.dealCard(c);
  }

  @Override
  public void register(SubscriberCard o) {
    subscribers.add(o);
  }

  @Override
  public void unregister(SubscriberCard o) {
    int observerIndex = subscribers.indexOf(o);
    subscribers.remove(observerIndex);
  }

  @Override
  public void notifyObserver(Player player, Iterable<Card> hand) {
    for (SubscriberCard s : subscribers) {
      s.updateDealer(this, this.getHand());
    }  
  }

}