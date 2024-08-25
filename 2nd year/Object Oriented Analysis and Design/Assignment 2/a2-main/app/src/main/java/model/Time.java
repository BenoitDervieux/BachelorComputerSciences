package model;

import java.util.ArrayList;
import java.util.List;
import model.interfaces.TimeObserver;

/**
 * Concept class for time prototyping.
 */
public class Time {
  private int currentDay;
  private List<TimeObserver> observers = new ArrayList<>();

  public Time() {
    currentDay = 0;
  }

  public void advanceDay() {
    currentDay++;
    notifyObservers();
  }

  public int getCurrentDay() {
    return currentDay;
  }

  public void addObserver(TimeObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(TimeObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all subscribers.
   */
  public void notifyObservers() {
    for (TimeObserver observer : observers) {
      observer.update(currentDay);
    }
  }
}
