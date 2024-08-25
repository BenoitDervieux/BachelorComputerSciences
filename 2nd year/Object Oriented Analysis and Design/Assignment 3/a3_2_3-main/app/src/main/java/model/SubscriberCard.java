package model;

/**
 * Interface subscriber for the observer pattern.
 */
public interface SubscriberCard {
  void updateDealer(Dealer dealer, Iterable<Card> hand);

  void updatePlayer(Player player, Iterable<Card> hand);
}
