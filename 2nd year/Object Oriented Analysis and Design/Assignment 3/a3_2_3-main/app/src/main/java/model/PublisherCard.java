package model;

/**
 * This is the publisher interface to implement the observer pattern.
 */
public interface PublisherCard {

  public void register(SubscriberCard o);

  public void unregister(SubscriberCard o);
  
  public void notifyObserver(Player player, Iterable<Card> hand);
    
}
