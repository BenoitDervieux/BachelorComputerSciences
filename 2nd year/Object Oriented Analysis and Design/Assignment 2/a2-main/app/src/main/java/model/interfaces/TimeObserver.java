package model.interfaces;


/**
 * Interface for contract to keep track of time.
 */
public interface TimeObserver {
  public void update(int currentDayCount);
}
