//Please uncomment the player-list constructor to activate the tests

/*
package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import model.Card.Mutable;
import model.rules.NormalWinStrategy;
import model.rules.PlayerWinGameStrategy;
import org.junit.jupiter.api.Test;*/

/**
 * This class aims to test the different strategy.
 * Comment out the constructor in the player class to use it.
 */
/* 
public class TestStrategy {

    
  // Here we test the player Win startegy
  // Case 1, scores are equal
  // Case 2, player is higher than dealer but equal 21
  // Case 3, player is higher than dealer but higher 21
  // Case 4, player is 21 but dealer higher than 21

  @Test
  void playerWinStrategyEqual() {
    Card.Mutable twoOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.King);
    twoOfHeartD.show(true);
    Card.Mutable  threeOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Six);
    threeOfHeartD.show(true);
    Card.Mutable  fourOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeartD.show(true);
    LinkedList<Card.Mutable> listD = new LinkedList<Card.Mutable>();
    listD.add(twoOfHeartD);
    listD.add(threeOfHeartD);
    listD.add(fourOfHeartD);
    Card.Mutable twoOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.King);
    twoOfHeartP.show(true);
    Card.Mutable  threeOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Six);
    threeOfHeartP.show(true);
    Card.Mutable  fourOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeartP.show(true);
    LinkedList<Card.Mutable> listP = new LinkedList<Card.Mutable>();
    listP.add(twoOfHeartP);
    listP.add(threeOfHeartP);
    listP.add(fourOfHeartP);
    Player player = new Player(listP);
    Player dealer = new Player(listD);
    PlayerWinGameStrategy playerWinner = new PlayerWinGameStrategy();
    assertFalse(playerWinner.isDealerWinner(dealer, player));
  }

  @Test
  void playerWinStrategyPlayerMoreDealerLess23() {
    Card.Mutable kingOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeartD.show(true);
    Card.Mutable  queenOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Queen);
    queenOfHeartD.show(true);
    LinkedList<Card.Mutable> listD = new LinkedList<Card.Mutable>();
    listD.add(kingOfHeartD);
    listD.add(queenOfHeartD);
    Card.Mutable kingOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeartP.show(true);
    Card.Mutable  aceOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Ace);
    aceOfHeartP.show(true);
    LinkedList<Card.Mutable> listP = new LinkedList<Card.Mutable>();
    listP.add(kingOfHeartP);
    listP.add(aceOfHeartP);
    Player player = new Player(listP);
    Player dealer = new Player(listD);
    PlayerWinGameStrategy playerWinner = new PlayerWinGameStrategy();
    assertFalse(playerWinner.isDealerWinner(dealer, player));
  }

  @Test
  void playerWinStrategyPlayerMoreDealerMore23() {
    Card.Mutable kingOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeartD.show(true);
    Card.Mutable  queenOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Queen);
    queenOfHeartD.show(true);
    LinkedList<Card.Mutable> listD = new LinkedList<Card.Mutable>();
    listD.add(kingOfHeartD);
    listD.add(queenOfHeartD);
    Card.Mutable kingOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeartP.show(true);
    Card.Mutable  queenOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Queen);
    queenOfHeartP.show(true);
    Card.Mutable  jackOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Knight);
    jackOfHeartP.show(true);
    LinkedList<Card.Mutable> listP = new LinkedList<Card.Mutable>();
    listP.add(kingOfHeartP);
    listP.add(queenOfHeartP);
    listP.add(jackOfHeartP);
    Player player = new Player(listP);
    Player dealer = new Player(listD);
    PlayerWinGameStrategy playerWinner = new PlayerWinGameStrategy();
    assertTrue(playerWinner.isDealerWinner(dealer, player));
  }

  @Test
  void playerWinStrategyPlayerWithDealerMoreThan23() {
    Card.Mutable kingOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeartD.show(true);
    Card.Mutable  queenOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Queen);
    queenOfHeartD.show(true);
    Card.Mutable  jackOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Knight);
    jackOfHeartD.show(true);
    LinkedList<Card.Mutable> listD = new LinkedList<Card.Mutable>();
    listD.add(kingOfHeartD);
    listD.add(queenOfHeartD);
    listD.add(jackOfHeartD);
    Card.Mutable kingOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeartP.show(true);
    Card.Mutable  aceOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Ace);
    aceOfHeartP.show(true);
    LinkedList<Card.Mutable> listP = new LinkedList<Card.Mutable>();
    listP.add(kingOfHeartP);
    listP.add(aceOfHeartP);
    Player player = new Player(listP);
    Player dealer = new Player(listD);
    PlayerWinGameStrategy playerWinner = new PlayerWinGameStrategy();
    assertFalse(playerWinner.isDealerWinner(dealer, player));
  }

  // Here we test the dealer Win startegy

  @Test
  void dealerWinStrategyEqual() {
    Card.Mutable twoOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.King);
    twoOfHeartD.show(true);
    Card.Mutable  threeOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Six);
    threeOfHeartD.show(true);
    Card.Mutable  fourOfHeartD = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeartD.show(true);
    LinkedList<Card.Mutable> listD = new LinkedList<Card.Mutable>();
    listD.add(twoOfHeartD);
    listD.add(threeOfHeartD);
    listD.add(fourOfHeartD);
    Card.Mutable twoOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.King);
    twoOfHeartP.show(true);
    Card.Mutable  threeOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Six);
    threeOfHeartP.show(true);
    Card.Mutable  fourOfHeartP = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeartP.show(true);
    LinkedList<Card.Mutable> listP = new LinkedList<Card.Mutable>();
    listP.add(twoOfHeartP);
    listP.add(threeOfHeartP);
    listP.add(fourOfHeartP);
    Player player = new Player(listP);
    Player dealer = new Player(listD);
    NormalWinStrategy dealerWinner = new NormalWinStrategy();
    assertTrue(dealerWinner.isDealerWinner(dealer, player));
  }
    
}*/