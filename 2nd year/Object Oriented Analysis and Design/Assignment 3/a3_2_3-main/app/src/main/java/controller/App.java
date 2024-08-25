package controller;

import model.Game;
import model.SubscriberCard;
import model.Visiteur;
import model.rules.AbstractRulesFactory;
import view.EnglishView;
import view.View;

/**
 * Starts the application using the console.
 */
public class App {
  /**
   * Starts the game.

  * @param args Not used.
  */
  public static void main(String[] args) {


    View v = new EnglishView(); // new SwedishView();
    controller.Player ctrl = new Player(v);
    //Instantiate rukes here
    AbstractRulesFactory rules = new model.rules.EasyRules();
    rules.accept((Visiteur) v);
    Game g = new Game((SubscriberCard) ctrl, rules);

    while (ctrl.play(g, v)) {

    }
  }
}