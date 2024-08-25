/*
package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import model.Card.Mutable;
import model.rules.Soft17Strategy;
import org.junit.jupiter.api.Test;*/

/**¨
 * Class to test the soft17 strategy.
 */
/*
public class Soft17Test {

  @Test
  void soft17StrategyTestLessThan17() {
    Card.Mutable twoOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Two);
    twoOfHeart.show(true);
    Card.Mutable  threeOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Three);
    threeOfHeart.show(true);
    Card.Mutable  fourOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeart.show(true);
    LinkedList<Card.Mutable> list = new LinkedList<Card.Mutable>();
    list.add(twoOfHeart);
    list.add(threeOfHeart);
    list.add(fourOfHeart);
    Player dealer = new Player(list);
    Soft17Strategy softStrategy17 = new Soft17Strategy();
    assertTrue(softStrategy17.doHit(dealer));
  }

  @Test
  void soft17StrategyTestMoreThan17() {
    Card.Mutable kingOfHeart = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeart.show(true);
    Card.Mutable  queenOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Queen);
    queenOfHeart.show(true);
    Card.Mutable  fourOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeart.show(true);
    LinkedList<Card.Mutable> list = new LinkedList<Card.Mutable>();
    list.add(kingOfHeart);
    list.add(queenOfHeart);
    list.add(fourOfHeart);
    Player dealer = new Player(list);
    assertEquals(24, dealer.calcScore());
    //assertFalse(softStrategy17.doHit(dealer));
  }

  @Test
  void soft17Strategy17NoAceEquals() {
    Card.Mutable kingOfHeart = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeart.show(true);
    Card.Mutable  threeOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Three);
    threeOfHeart.show(true);
    Card.Mutable  fourOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeart.show(true);
    LinkedList<Card.Mutable> list = new LinkedList<Card.Mutable>();
    list.add(kingOfHeart);
    list.add(threeOfHeart);
    list.add(fourOfHeart);
    Player dealer = new Player(list);
    Soft17Strategy softStrategy17 = new Soft17Strategy();
    assertEquals(17, dealer.calcScore());
    assertFalse(softStrategy17.doHit(dealer));
  }

  @Test
  void soft17Strategy17NoAceFalse() {
    Card.Mutable kingOfHeart = new Mutable(Card.Color.Hearts, Card.Value.King);
    kingOfHeart.show(true);
    Card.Mutable  threeOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Three);
    threeOfHeart.show(true);
    Card.Mutable  fourOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeart.show(true);
    LinkedList<Card.Mutable> list = new LinkedList<Card.Mutable>();
    list.add(kingOfHeart);
    list.add(threeOfHeart);
    list.add(fourOfHeart);
    Player dealer = new Player(list);
    Soft17Strategy softStrategy17 = new Soft17Strategy();
    assertFalse(softStrategy17.doHit(dealer));
  }

  @Test
  void soft17Strategy17AceEquals3Cards() {
    Card.Mutable kingOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Ace);
    kingOfHeart.show(true);
    Card.Mutable  twoOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Two);
    twoOfHeart.show(true);
    Card.Mutable  fourOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Four);
    fourOfHeart.show(true);
    LinkedList<Card.Mutable> list = new LinkedList<Card.Mutable>();
    list.add(kingOfHeart);
    list.add(twoOfHeart);
    list.add(fourOfHeart);
    Player dealer = new Player(list);
    Soft17Strategy softStrategy17 = new Soft17Strategy();
    assertTrue(softStrategy17.doHit(dealer));
  }

  @Test
  void soft17Strategy17AceEquals2Cards() {
    Card.Mutable kingOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Ace);
    kingOfHeart.show(true);
    Card.Mutable  sixOfHeart = new Mutable(Card.Color.Hearts, Card.Value.Six);
    sixOfHeart.show(true);
    LinkedList<Card.Mutable> list = new LinkedList<Card.Mutable>();
    list.add(kingOfHeart);
    list.add(sixOfHeart);
    Player dealer = new Player(list);
    Soft17Strategy softStrategy17 = new Soft17Strategy();
    assertTrue(softStrategy17.doHit(dealer));
  } 
}*/
