package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents a player in the Black Jack game. A Player has a hand of cards.
 */
public class Player implements PublisherCard {

  private List<Card.Mutable> hand;
  protected final int maxScore = 21;
  private ArrayList<SubscriberCard> subscribers; 

  public Player() {
    hand = new LinkedList<Card.Mutable>();
    subscribers = new ArrayList<SubscriberCard>();
  }

  /*
   * This constructor is for testing purpose.
   * Comment it out if you want to test the different strategies

   * @param hand is the hand of the player.
  
  public Player(List<Card.Mutable> hand) {
    hand = new LinkedList<Card.Mutable>();
    for (Card.Mutable c : hand) {
      hand.add(c);
    }
  }*/

  /**
   * Adds a card to the Player's hand. 

   * @param addToHand The card to add to the hand.
   */
  public void dealCard(Card.Mutable addToHand) {
    hand.add(addToHand);
    notifyObserver(this, getHand());
  }

  /**
   * Returns the cards in thand.

   * @return the cards in the Player's hand
   */
  public Iterable<Card> getHand() {
    return new LinkedList<Card>(hand);
  }

  /**
   * Returns the number of card own by the player.

   * @return an int.
   */
  public int getNumberOfCards() {
    return hand.size();
  }

  /**
   * Removes all cards from the hand.
   */
  public void clearHand() {
    hand.clear();
  }

  /**
   * Shows all cards in the hand.
   */
  public void showHand() {
    for (Card.Mutable c : hand) {
      c.show(true);
    }
  }

  /**
   * Calculates the score of the hand according to Black Jack rules.

   * @return The score.
   */
  public int calcScore() {
    // the number of scores is dependant on the number of scorable values
    // as it seems there is no way to do this check at compile time in java ?!
    // cardScores[13] = {...};
    int[] cardScores = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
    assert (cardScores.length == Card.Value.Count.ordinal())
        : "Card Scores array size does not match number of card values";

    int score = 0;

    for (Card c : getHand()) {
      if (c.getValue() != Card.Value.Hidden) {
        score += cardScores[c.getValue().ordinal()];
      }
    }

    if (score > maxScore) {
      for (Card c : getHand()) {
        if (c.getValue() == Card.Value.Ace && score > maxScore) {
          score -= 10;
        }
      }
    }

    return score;
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
      if (!(s instanceof Dealer)) {
        s.updatePlayer(this, this.getHand());
      }
    }  
  }

}
